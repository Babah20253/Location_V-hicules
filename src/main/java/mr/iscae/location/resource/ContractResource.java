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
        return Response.ok(started).build();
    }

    @POST
    @Path("/{id}/close")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response closeContract(@PathParam("id") Long id, Contract updatedContract) {
        Contract closed = contractService.closeContract(id, updatedContract);
        if (closed != null) {
            return Response.ok(closed).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("Cannot close contract").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllContracts() {
        List<Contract> contracts = contractService.getAllContracts();
        return Response.ok(contracts).build();
    }
}
