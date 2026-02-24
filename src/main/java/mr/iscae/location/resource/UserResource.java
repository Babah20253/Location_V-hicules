package mr.iscae.location.resource;

import mr.iscae.location.model.User;
import mr.iscae.location.service.UserService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    private UserService userService = new UserService();

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerClient(User user) {
        User created = userService.registerClient(user);
        return Response.ok(created).build();
    }

    @POST
    @Path("/manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createManager(User user) {
        User created = userService.createManager(user);
        return Response.ok(created).build();
    }
}
