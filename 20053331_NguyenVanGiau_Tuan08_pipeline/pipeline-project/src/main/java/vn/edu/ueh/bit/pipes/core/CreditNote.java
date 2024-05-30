package vn.edu.ueh.bit.pipes.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CreditNote {
    private long creditNote;
    private boolean cancellation;
    private String notes;


    public CreditNote(long creditNote, String notes) {
        this.creditNote = creditNote;
        this.notes = notes;
        this.cancellation = false;
    }

    public long getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(long creditNote) {
        this.creditNote = creditNote;
    }

    public boolean isCancellation() {
        return cancellation;
    }

    public void setCancellation(boolean cancellation) {
        this.cancellation = cancellation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "CreditNote{" +
                "creditNote=" + creditNote +
                ", cancellation=" + cancellation +
                ", notes='" + notes + '\'' +
                '}';
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
