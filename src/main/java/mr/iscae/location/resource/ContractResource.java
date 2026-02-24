package mr.iscae.location.resource;

import mr.iscae.location.model.Contract;
import mr.iscae.location.service.ContractService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contracts")
public class ContractResource {
    private ContractService contractService = new ContractService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response startContract(Contract contract) {
        Contract started = contractService.startContract(contract);
        if (started == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de démarrer le contrat").build();
        }
        return Response.status(Response.Status.CREATED).entity(started).build();
    }

    @POST
    @Path("/{id}/close")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response closeContract(@PathParam("id") Long id, Contract updatedContract) {
        Contract closed = contractService.closeContract(id, updatedContract);
        if (closed == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Impossible de clôturer le contrat").build();
        }
        return Response.ok(closed).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return Response.ok(contracts).build();
    }
}
