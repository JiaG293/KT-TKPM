package vn.edu.ueh.bit.pipes.core.entities;

import vn.edu.ueh.bit.pipes.core.InvoiceInfo;

public interface IMessage {
    InvoiceInfo getInvoiceInfo();
    void setInvoiceInfo(InvoiceInfo invoiceInfo);
    String getFileContent();
    void setFileContent(String fileContent);
}
