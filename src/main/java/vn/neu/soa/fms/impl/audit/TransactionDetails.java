package vn.neu.soa.fms.impl.audit;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDetails {
    private Date date;
    private BigDecimal amount;
    private String description;

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }


}
