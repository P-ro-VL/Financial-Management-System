package vn.neu.soa.fms.services;
import java.io.Serializable;
import java.util.Date;
public class AccountingBook {
    private int id;
    private Date date;
    private String referenceCode;
    private Date referenceDate;
    private String description;
    private double debit;
    private double credit;

    public AccountingBook(int id, Date date, String referenceCode, Date referenceDate, String description, double debit, double credit) {
        this.id = id;
        this.date = date;
        this.referenceCode = referenceCode;
        this.referenceDate = referenceDate;
        this.description = description;
        this.debit = debit;
        this.credit = credit;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
