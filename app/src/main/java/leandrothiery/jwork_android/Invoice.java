package leandrothiery.jwork_android;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Invoice of applied Job by Jobseeker
 *
 * @author Leandro Thiery
 * @version 06/25/2021
 */
public class Invoice implements Serializable {
    private int id;
    private ArrayList<Job> jobs;
    private int totalFee;
    private String date;
    private String invoiceStatus;
    private String paymentType;
    private String referralCode = null;

    /**
     * Constructor of Invoice
     *
     * @param id            id of invoice
     * @param jobs          list of jobs
     * @param totalFee      totalFee from all jobs
     * @param date          date of invoice
     * @param invoiceStatus invoice status
     * @param paymentType   invoice payment type
     */
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


    /**
     * @return int id of invoice
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id of invoice
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return ArrayList of Jobs
     */
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    /**
     * @param jobs replace with new ArrayList of Jobs
     */
    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    /**
     * @return total fee of invoice
     */
    public int getTotalFee() {
        return totalFee;
    }

    /**
     * @param totalFee set a new total fee
     */
    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * @return date of invoice
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date set a new date (String)
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return status of invoice
     */
    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * @param invoiceStatus change invoice status
     */
    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * @return payment type of invoice
     */
    public String getPaymentType() {
        return paymentType;
    }

    /**
     * @param paymentType set a new payment type
     */
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * @return referral code of invoice (for Ewallet Payment Type)
     */
    public String getReferralCode() {
        return referralCode;
    }

    /**
     * @param referralCode set a new referral code
     */
    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

}
