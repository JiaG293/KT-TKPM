package vn.edu.ueh.bit.pipes.client;

import vn.edu.ueh.bit.pipes.core.*;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;
import vn.edu.ueh.bit.pipes.pipes.Pipeline;
import vn.edu.ueh.bit.pipes.pipes.PipelineBase;
import vn.edu.ueh.bit.pipes.process.read.credit.CreditNoteReader;
import vn.edu.ueh.bit.pipes.process.read.invoice.InvoiceReader;
import vn.edu.ueh.bit.pipes.process.read.note.NoteReader;
import vn.edu.ueh.bit.pipes.process.read.payment.PaymentReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IFilter<IMessage>> filters = new ArrayList<>();
        IMessage message = new Message();
        message.setFileContent("{\"invoices\":[{\"invoiceId\":1,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 1\"},{\"invoiceId\":2,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 2\"},{\"invoiceId\":3,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 3\"}],\"payments\":[{\"paymentId\":1,\"custId\":1,\"cardNumber\":\"7485-2222-3456-2435\",\"cvv\":\"111\"},{\"paymentId\":2,\"custId\":1,\"cardNumber\":\"7485-2222-3456-2435\",\"cvv\":\"111\"}],\"notes\":[{\"nodeId\":1,\"note\":\"sample note 1\",\"deliveryAdd\":\"12 NVB F4 GV\",\"isDelivery\":false},{\"nodeId\":2,\"note\":\"sample note 2\",\"deliveryAdd\":\"1 NTS F4 GV\",\"isDelivery\":false}],\"creditNotes\":[{\"creditNote\":1,\"cancellation\":false,\"notes\":\"Sample note 1\"},{\"creditNote\":2,\"cancellation\":false,\"notes\":\"Sample note 2\"}]}\n");
        // 1. Create the filter list first
        filters.add(new InvoiceReader());
        filters.add(new PaymentReader());
        filters.add(new NoteReader());
        filters.add(new CreditNoteReader());
        // 2. Create Pipe with list of filter
        PipelineBase<IMessage> pipeLine = new Pipeline(filters);
        // 3. Perform execution
        IMessage result = pipeLine.ProcessFilters(message);
        System.out.println(result);
    }

    static IMessage createSampleMessage() {
        IMessage message = new Message();
        message.setFileContent("{\"invoices\":[{\"invoiceId\":1,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 1\"},{\"invoiceId\":2,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 2\"},{\"invoiceId\":3,\"custId\":1,\"productId\":1,\"quantity\":100,\"description\":\"sample invoice 3\"}],\"payments\":[{\"paymentId\":1,\"custId\":1,\"cardNumber\":\"7485-2222-3456-2435\",\"cvv\":\"111\"},{\"paymentId\":2,\"custId\":1,\"cardNumber\":\"7485-2222-3456-2435\",\"cvv\":\"111\"}],\"notes\":[{\"nodeId\":1,\"note\":\"sample note 1\",\"deliveryAdd\":\"12 NVB F4 GV\",\"isDelivery\":false},{\"nodeId\":2,\"note\":\"sample note 2\",\"deliveryAdd\":\"1 NTS F4 GV\",\"isDelivery\":false}],\"creditNotes\":[{\"creditNote\":1,\"cancellation\":false,\"notes\":\"Sample note 1\"},{\"creditNote\":2,\"cancellation\":false,\"notes\":\"Sample note 2\"}]}\n");
        InvoiceInfo invoiceInfo = new InvoiceInfo();

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(new Invoice(1L, 1L, 1L, 100, "sample invoice 1"));
        invoices.add(new Invoice(2L, 1L, 1L, 100, "sample invoice 2"));
        invoices.add(new Invoice(3L, 1L, 1L, 100, "sample invoice 3"));
        invoiceInfo.setInvoices(invoices);

        List<Note> notes = new ArrayList<>();
        notes.add(new Note(1L, "sample note 1", "12 NVB F4 GV", false));
        notes.add(new Note(2L, "sample note 2", "1 NTS F4 GV", false));
        invoiceInfo.setNotes(notes);

        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1L, 1L, "7485-2222-3456-2435", "111"));
        payments.add(new Payment(2L, 1L, "7485-2222-3456-2435", "111"));
        invoiceInfo.setPayments(payments);

        List<CreditNote> creditNotes = new ArrayList<>();
        creditNotes.add(new CreditNote(1L, "Sample note 1"));
        creditNotes.add(new CreditNote(2L, "Sample note 2"));
        invoiceInfo.setCreditNotes(creditNotes);

        message.setInvoiceInfo(invoiceInfo);
        System.out.println(invoiceInfo.toJson());
        return message;
    }
}
