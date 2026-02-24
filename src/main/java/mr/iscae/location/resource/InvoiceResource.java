package mr.iscae.location.resource;

import mr.iscae.location.model.Invoice;
import mr.iscae.location.service.InvoiceService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/invoices")
public class InvoiceResource {
    private InvoiceService invoiceService = new InvoiceService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateInvoice(Invoice invoice) {
        Invoice created = invoiceService.generateInvoice(invoice);
        return Response.ok(created).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return Response.ok(invoices).build();
    }
}
