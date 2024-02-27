package vn.neu.soa.fms.impl.audit;

import vn.neu.soa.fms.services.TransactionDetails;

import java.math.BigDecimal;
import java.util.Date;

public class Transaction {
    private int id;
    private Date date;
    private BigDecimal amount;
    private String description;
    private TransactionDetails details;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public TransactionDetails getDetails() {
        return details;
    }


}
