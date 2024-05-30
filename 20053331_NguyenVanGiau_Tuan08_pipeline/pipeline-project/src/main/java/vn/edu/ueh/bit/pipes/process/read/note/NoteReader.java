package vn.edu.ueh.bit.pipes.process.read.note;

import vn.edu.ueh.bit.pipes.core.Note;
import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.ArrayList;
import java.util.List;

public class NoteReader implements IFilter<IMessage> {
    @Override
    public IMessage execute(IMessage message) {
        //1. Read the note content from the file content comes with the message
        List<Note> notes=new ArrayList<>();
        notes.add(new Note(1L,"sample note 1","12 NVB F4 GV",false));
        notes.add(new Note(2L,"sample note 2","1 NTS F4 GV",false));

        // 2. Update the message with the notes read

        message.getInvoiceInfo().setNotes(notes);

        // 3. Return the updated message back to the caller


        return message;
    }
}
