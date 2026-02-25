package mr.iscae.location.resource;

import mr.iscae.location.model.Vehicle;
import mr.iscae.location.service.VehicleService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/vehicules")
public class VehicleResource {
    private VehicleService vehicleService = new VehicleService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addVehicle(Vehicle vehicle) {
        Vehicle created = vehicleService.addVehicle(vehicle);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return Response.ok(vehicles).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteVehicle(@PathParam("id") Long id) {
        boolean deleted = vehicleService.deleteVehicle(id);
        if (deleted) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Vehicle not found").build();
        }
    }

    @PUT
    @Path("/{id}") 
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Response updateVehicle(@PathParam("id") Long id, Vehicle updated) {
    Vehicle vehicle = vehicleService.getAllVehicles().stream()
        .filter(v -> v.getId().equals(id))
        .findFirst()
        .orElse(null);
    if (vehicle == null) {
        return Response.status(Response.Status.NOT_FOUND).entity("Vehicle not found").build();
    }
    vehicle.setDailyRate(updated.getDailyRate());
    return Response.ok(vehicle).build();
}  
    
}
