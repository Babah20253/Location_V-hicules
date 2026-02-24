package mr.iscae.location.resource;

import mr.iscae.location.model.Reservation;
import mr.iscae.location.service.ReservationService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/reservations")
public class ReservationResource {
    private ReservationService reservationService = new ReservationService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createReservation(Reservation reservation) {
        Reservation created = reservationService.createReservation(reservation);
        return Response.ok(created).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return Response.ok(reservations).build();
    }

    @POST
    @Path("/{id}/cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelReservation(@PathParam("id") Long id) {
        boolean cancelled = reservationService.cancelReservation(id);
        if (cancelled) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot cancel reservation").build();
        }
    }

    @POST
    @Path("/{id}/validate")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateReservation(@PathParam("id") Long id, Long vehicleId) {
        Reservation validated = reservationService.validateReservation(id, vehicleId);
        if (validated != null) {
            return Response.ok(validated).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot validate reservation").build();
        }
    }
}
