
package mr.iscae.location.service;

import mr.iscae.location.model.Reservation;
import mr.iscae.location.model.User;
import mr.iscae.location.model.Vehicle;
import mr.iscae.location.util.DatabaseMemory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService {
    private static AtomicLong idCounter = new AtomicLong(1);

    public Reservation createReservation(Reservation reservation) {
        // Business rules
        User client = DatabaseMemory.users.get(reservation.getClientId());
        if (client == null) return null;
        int age = DatabaseMemory.ageFromBirthDate(client.getBirthDate());
        if (age < 21) return null;
        if (reservation.getVehicleCategory().equalsIgnoreCase("LUXE") && age < 25) return null;
        int licenseYears = DatabaseMemory.yearsBetween(client.getDrivingLicenseDate());
        if ((reservation.getVehicleCategory().equalsIgnoreCase("SUV") || reservation.getVehicleCategory().equalsIgnoreCase("LUXE")) && licenseYears < 2) return null;
        java.time.LocalDateTime start = java.time.LocalDateTime.parse(reservation.getStartDateTime());
        java.time.LocalDateTime end = java.time.LocalDateTime.parse(reservation.getEndDateTime());
        if (start.isBefore(java.time.LocalDateTime.now())) return null;
        if (end.isBefore(start)) return null;
        // Empêcher plus d'une réservation active (PENDING ou VALIDATED) par client
        boolean hasActive = DatabaseMemory.reservations.values().stream()
            .anyMatch(r -> r.getClientId().equals(reservation.getClientId()) &&
                (r.getStatus() == Reservation.Status.PENDING || r.getStatus() == Reservation.Status.VALIDATED));
        if (hasActive) return null;
        // Filtrer les véhicules disponibles sans chevauchement
        List<Vehicle> availableVehicles = DatabaseMemory.vehicles.values().stream()
            .filter(v -> v.getCategory().name().equalsIgnoreCase(reservation.getVehicleCategory()) && v.getStatus() == Vehicle.Status.AVAILABLE)
            .filter(v -> DatabaseMemory.reservations.values().stream()
                .filter(r -> r.getVehicleId() != null && r.getVehicleId().equals(v.getId()))
                .noneMatch(r -> DatabaseMemory.datesOverlap(r.getStartDateTime(), r.getEndDateTime(), reservation.getStartDateTime(), reservation.getEndDateTime())))
            .collect(Collectors.toList());
        if (availableVehicles.isEmpty()) return null;
        Vehicle vehicle = availableVehicles.get(0);
        reservation.setId(idCounter.getAndIncrement());
        reservation.setStatus(Reservation.Status.PENDING);
        reservation.setVehicleId(vehicle.getId());
        DatabaseMemory.reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return DatabaseMemory.reservations.values().stream().collect(Collectors.toList());
    }

    public boolean cancelReservation(Long id) {
        Reservation res = DatabaseMemory.reservations.get(id);
        if (res != null && res.getStatus() == Reservation.Status.PENDING) {
            java.time.LocalDateTime start = java.time.LocalDateTime.parse(res.getStartDateTime());
            if (java.time.Duration.between(java.time.LocalDateTime.now(), start).toHours() < 48) return false;
            res.setStatus(Reservation.Status.CANCELLED);
            return true;
        }
        return false;
    }

    public Reservation validateReservation(Long id, Long vehicleId) {
        Reservation res = DatabaseMemory.reservations.get(id);
        if (res != null && res.getStatus() == Reservation.Status.PENDING) {
            Vehicle vehicle = DatabaseMemory.vehicles.get(vehicleId);
            if (vehicle == null || vehicle.getStatus() != Vehicle.Status.AVAILABLE) return null;
            res.setStatus(Reservation.Status.VALIDATED);
            res.setVehicleId(vehicleId);
            vehicle.setStatus(Vehicle.Status.RENTED);
            return res;
        }
        return null;
    }
}
