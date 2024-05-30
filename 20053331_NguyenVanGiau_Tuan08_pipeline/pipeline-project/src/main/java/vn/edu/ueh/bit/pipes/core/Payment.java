package vn.edu.ueh.bit.pipes.core;

import com.google.gson.Gson;

public class Payment {
    private long paymentId;
    private long custId;
    private String cardNumber;
    private String cvv; // Card Verification Value

    public Payment(long paymentId, long custId, String cardNumber, String cvv) {
        this.paymentId = paymentId;
        this.custId = custId;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public long getCustId() {
        return custId;
    }

    public void setCustId(long custId) {
        this.custId = custId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", custId=" + custId +
                ", cardNumber='" + cardNumber + '\'' +
                ", cvt='" + cvv + '\'' +
                '}';
    }
    public String toJson(){
        Gson gson=new Gson();
        return gson.toJson(this);
    }
}
