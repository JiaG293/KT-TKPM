package vn.edu.ueh.bit.pipes.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;


public class InvoiceInfo {
    private List<Invoice> invoices;
    private List<Payment> payments;
    private List<Note> notes;
    private List<CreditNote> creditNotes;

    public InvoiceInfo() {
        invoices = new ArrayList<>();
        payments = new ArrayList<>();
        notes = new ArrayList<>();
        creditNotes = new ArrayList<>();
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<CreditNote> getCreditNotes() {
        return creditNotes;
    }

    public void setCreditNotes(List<CreditNote> creditNotes) {
        this.creditNotes = creditNotes;
    }

    public String toJson() {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    @Override
    public String toString() {
        return "InvoiceInfo{" +
                "invoices=" + invoices +
                ", payments=" + payments +
                ", notes=" + notes +
                ", creditNotes=" + creditNotes +
                '}';
    }
}
