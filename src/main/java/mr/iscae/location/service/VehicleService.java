package mr.iscae.location.service;

import mr.iscae.location.model.Vehicle;
import mr.iscae.location.util.DatabaseMemory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleService {
    private static AtomicLong idCounter = new AtomicLong(1);

    public Vehicle addVehicle(Vehicle vehicle) {
        vehicle.setId(idCounter.getAndIncrement());
        DatabaseMemory.vehicles.put(vehicle.getId(), vehicle);
        return vehicle;
    }

    public List<Vehicle> getAllVehicles() {
        return DatabaseMemory.vehicles.values().stream().collect(Collectors.toList());
    }

    public boolean deleteVehicle(Long id) {
        return DatabaseMemory.vehicles.remove(id) != null;
    }
}
