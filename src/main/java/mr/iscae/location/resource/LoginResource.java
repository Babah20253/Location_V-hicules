package mr.iscae.location.resource;

import mr.iscae.location.model.User;
import mr.iscae.location.service.UserService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {
    private UserService userService = new UserService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        User found = userService.login(user.getUsername(), user.getPassword());
        if (found != null) {
            found.setPassword(null); // Ne jamais retourner le mot de passe
            return Response.ok(found).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }
}
