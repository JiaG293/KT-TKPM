package vn.edu.ueh.bit.pipes.process.read.invoice;

import vn.edu.ueh.bit.pipes.core.Invoice;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.ArrayList;
import java.util.List;

public class InvoiceReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        //
        // 1. Read the invoice data from file content comes inside the message
        //parse to structured data
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L, 1L, 1L, 100, "sample invoice 1"));
        invoices.add(new Invoice(2L, 1L, 1L, 100, "sample invoice 2"));
        invoices.add(new Invoice(3L, 1L, 1L, 100, "sample invoice 3"));

        // 2. Set the invoice data in invoiceInfo object comes with the message
        message.getInvoiceInfo().setInvoices(invoices);

        // 3. Return the update message objected back to caller
        return message;
    }
}
//vovanhai-ueh