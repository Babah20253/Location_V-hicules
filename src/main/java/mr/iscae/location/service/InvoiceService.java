package mr.iscae.location.service;

import mr.iscae.location.model.Invoice;
import mr.iscae.location.util.DatabaseMemory;
import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceService {
        public List<Invoice> getInvoicesByClient(Long clientId) {
            return DatabaseMemory.invoices.values().stream()
                .filter(inv -> inv.getClientId() != null && inv.getClientId().equals(clientId))
                .collect(Collectors.toList());
        }
    private static AtomicLong idCounter = new AtomicLong(1);

    public Invoice generateInvoice(Invoice invoice) {
        invoice.setId(idCounter.getAndIncrement());
        DatabaseMemory.invoices.put(invoice.getId(), invoice);
        return invoice;
    }

    public List<Invoice> getAllInvoices() {
        return DatabaseMemory.invoices.values().stream().collect(Collectors.toList());
    }
}
