
package mr.iscae.location.service;

import mr.iscae.location.model.Contract;
import mr.iscae.location.model.User;
import mr.iscae.location.model.Vehicle;
import mr.iscae.location.model.Reservation;
import mr.iscae.location.model.Invoice;
import mr.iscae.location.util.DatabaseMemory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.stream.Collectors;

public class ContractService {
    private static AtomicLong idCounter = new AtomicLong(1);

    public Contract startContract(Contract contract) {
        // Defensive checks
        Reservation reservation = DatabaseMemory.reservations.get(contract.getReservationId());
        User client = DatabaseMemory.users.get(reservation.getClientId());
        Vehicle vehicle = DatabaseMemory.vehicles.get(contract.getVehicleId());
        int age = DatabaseMemory.ageFromBirthDate(client.getBirthDate());
        if (age < 21) return null;
        if (vehicle.getCategory() == Vehicle.Category.LUXE && age < 25) return null;
        int licenseYears = DatabaseMemory.yearsBetween(client.getDrivingLicenseDate());
        if ((vehicle.getCategory() == Vehicle.Category.SUV || vehicle.getCategory() == Vehicle.Category.LUXE) && licenseYears < 2) return null;
        contract.setId(idCounter.getAndIncrement());
        DatabaseMemory.contracts.put(contract.getId(), contract);
        return contract;
    }

    public Contract closeContract(Long id, Contract updatedContract) {
        Contract contract = DatabaseMemory.contracts.get(id);
        if (contract != null) {
            contract.setActualReturn(updatedContract.getActualReturn());
            contract.setReturnMileage(updatedContract.getReturnMileage());
            contract.setFuelReturn(updatedContract.getFuelReturn());
            contract.setReturnState(updatedContract.getReturnState());
            contract.setDamagesCount(updatedContract.getDamagesCount());
            // Penalty calculation
            Reservation reservation = DatabaseMemory.reservations.get(contract.getReservationId());
            Vehicle vehicle = DatabaseMemory.vehicles.get(contract.getVehicleId());
            long rentalDays = DatabaseMemory.daysCeil(reservation.getStartDateTime(), reservation.getEndDateTime());
            double dailyRate = vehicle.getDailyRate();
            // Delay penalty
            java.time.LocalDateTime expectedReturn = java.time.LocalDateTime.parse(reservation.getEndDateTime());
            java.time.LocalDateTime actualReturn = java.time.LocalDateTime.parse(updatedContract.getActualReturn());
            long minutesLate = java.time.Duration.between(expectedReturn, actualReturn).toMinutes();
            long hoursLate = (long)Math.ceil(minutesLate / 60.0);
            double delayPenalty = hoursLate > 0 ? hoursLate * dailyRate * 0.2 : 0;
            // Fuel penalty
            double missingFuel = contract.getFuelStart() - contract.getFuelReturn();
            double fuelPenalty = missingFuel > 0 ? missingFuel * DatabaseMemory.FUEL_UNIT_PRICE * 1.3 : 0;
            // Damage penalty
            double damagePenalty = contract.getDamagesCount() * DatabaseMemory.DAMAGE_UNIT_PRICE;
            // Options cost
            double optionsCost = DatabaseMemory.optionsCost(reservation.getOptions(), rentalDays);
            // Total cost
            double total = dailyRate * rentalDays + optionsCost + delayPenalty + fuelPenalty + damagePenalty;
            contract.setPenalties(delayPenalty + fuelPenalty + damagePenalty);
            // Générer la facture via InvoiceService
            InvoiceService invoiceService = new InvoiceService();
            Invoice invoice = new Invoice();
            invoice.setContractId(contract.getId());
            invoice.setTotalCost(total);
            invoice.setBreakdown("Rental: " + rentalDays + " days x " + dailyRate + ", options: " + optionsCost + ", delay: " + delayPenalty + ", fuel: " + fuelPenalty + ", damages: " + damagePenalty);
            invoice.setDelayPenalty(delayPenalty);
            invoice.setFuelPenalty(fuelPenalty);
            invoice.setDamagePenalty(damagePenalty);
            invoice.setOptionsCost(optionsCost);
            invoice.setRentalDays(rentalDays);
            invoice.setDailyRate(dailyRate);
            invoice.setClientId(reservation.getClientId());
            invoiceService.generateInvoice(invoice);
            // Mettre à jour les statuts
            vehicle.setStatus(Vehicle.Status.AVAILABLE);
            reservation.setStatus(Reservation.Status.COMPLETED);
            return contract;
        }
        return null;
    }

    public List<Contract> getAllContracts() {
        return DatabaseMemory.contracts.values().stream().collect(Collectors.toList());
    }
}
