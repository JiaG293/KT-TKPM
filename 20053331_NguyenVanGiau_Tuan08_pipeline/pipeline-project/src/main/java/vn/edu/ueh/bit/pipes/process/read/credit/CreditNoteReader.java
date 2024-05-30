package vn.edu.ueh.bit.pipes.process.read.credit;

import vn.edu.ueh.bit.pipes.core.CreditNote;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.ArrayList;
import java.util.List;

public class CreditNoteReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        // 1. Read the credit notes from file content

        String json = message.getFileContent();

        //in this case, sample assign value instead of parsing data
        List<CreditNote> creditNotes=new ArrayList<>();
        creditNotes.add(new CreditNote(1L,"Sample note 1"));
        creditNotes.add(new CreditNote(2L,"Sample note 2"));

        // 2. Update the message object with credit notes read

        message.getInvoiceInfo().setCreditNotes(creditNotes);

        // 3. Return the updated message back to caller


        return message;
    }
}
