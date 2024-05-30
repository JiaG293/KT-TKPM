package vn.edu.ueh.bit.pipes.process.read.payment;

import vn.edu.ueh.bit.pipes.core.Payment;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.ArrayList;
import java.util.List;

public class PaymentReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        // 1. Read the payments from file Content
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1L,1L,"7485-2222-3456-2435","111"));
        payments.add(new Payment(2L,1L,"7485-2222-3456-2435","111"));

        // 2. Set the payments in Invoice Info object comes with the message

        message.getInvoiceInfo().setPayments(payments);

        // 3. Return the updated message object back to caller


        return message;
    }
}
