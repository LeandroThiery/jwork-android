package leandrothiery.jwork_android;

import java.io.Serializable;
import java.util.ArrayList;

public class Invoice implements Serializable {
    private int id;
    private ArrayList<Job> jobs;
    private int totalFee;
    private String date;
    private String invoiceStatus;
    private String paymentType;

    public Invoice(int id,
                   ArrayList<Job> jobs,
                   int totalFee,
                   String date,
                   String invoiceStatus,
                   String paymentType) {
        this.id = id;
        this.jobs = jobs;
        this.totalFee = totalFee;
        this.date = date;
        this.invoiceStatus = invoiceStatus;
        this.paymentType = paymentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public int getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
