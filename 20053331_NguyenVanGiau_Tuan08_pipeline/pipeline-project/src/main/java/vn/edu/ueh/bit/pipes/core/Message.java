package vn.edu.ueh.bit.pipes.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

public class Message implements IMessage {
    private String fileContent = "";
    private InvoiceInfo invoiceInfo;

    public Message() {
        invoiceInfo=new InvoiceInfo();
    }

    @Override
    public InvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    @Override
    public void setInvoiceInfo(InvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    @Override
    public String getFileContent() {
        return fileContent;
    }

    @Override
    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "fileContent='" + fileContent + '\'' +
                ", invoiceInfo=" + invoiceInfo +
                '}';
    }
    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}
