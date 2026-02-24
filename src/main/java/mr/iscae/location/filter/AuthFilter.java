package mr.iscae.location.filter;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        String method = requestContext.getMethod();
        // 1️⃣ Autoriser /login et /register sans authentification
        if (path.equalsIgnoreCase("login") || path.equalsIgnoreCase("register")) {
            return;
        }
        // Vérification des headers obligatoire pour le reste
        String userIdHeader = requestContext.getHeaderString("X-USER-ID");
        String roleHeader = requestContext.getHeaderString("X-ROLE");
        if (userIdHeader == null || roleHeader == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Authentication required").build());
            return;
        }
        Long userId;
        try {
            userId = Long.parseLong(userIdHeader);
        } catch (NumberFormatException e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Invalid user id").build());
            return;
        }
        mr.iscae.location.model.User user = mr.iscae.location.util.DatabaseMemory.users.get(userId);
        if (user == null || !user.getRole().name().equalsIgnoreCase(roleHeader)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("User not found or role mismatch").build());
            return;
        }
        // ADMIN only
        if ((path.startsWith("vehicules") && (method.equals("POST") || method.equals("PUT") || method.equals("DELETE"))) || path.startsWith("managers")) {
            if (!roleHeader.equalsIgnoreCase("ADMIN")) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Admin only").build());
                return;
            }
        }
        // MANAGER only
        if ((path.contains("reservations/validate") || path.contains("contrats/start") || path.contains("contrats/end") || path.contains("vehicules/status"))) {
            if (!roleHeader.equalsIgnoreCase("MANAGER")) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Manager only").build());
                return;
            }
        }
        // CLIENT only
        if ((path.contains("register") || (path.startsWith("reservations") && method.equals("POST")) || (path.startsWith("reservations") && method.equals("GET")) || path.contains("reservations/cancel") || (path.startsWith("vehicules") && method.equals("GET")) || (path.startsWith("invoices") && method.equals("GET")))) {
            if (!roleHeader.equalsIgnoreCase("CLIENT")) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).entity("Client only").build());
                return;
            }
        }
    }
}
