package vn.neu.soa.fms.impl.audit;

import java.math.BigDecimal;

public class AuditFinding {
    private AuditFindingType type;
    private Account account;
    private BigDecimal expectedBalance;
    private BigDecimal actualBalance;
    private Transaction transaction;
    private TransactionDetails expectedDetails;
    private TransactionDetails actualDetails;

    public AuditFindingType getType() {
        return type;
    }

    public void setType(AuditFindingType type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BigDecimal getExpectedBalance() {
        return expectedBalance;
    }

    public void setExpectedBalance(BigDecimal expectedBalance) {
        this.expectedBalance = expectedBalance;
    }

    public BigDecimal getActualBalance() {
        return actualBalance;
    }

    public void setActualBalance(BigDecimal actualBalance) {
        this.actualBalance = actualBalance;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionDetails getExpectedDetails() {
        return expectedDetails;
    }

    public void setExpectedDetails(TransactionDetails expectedDetails) {
        this.expectedDetails = expectedDetails;
    }

    public TransactionDetails getActualDetails() {
        return actualDetails;
    }

    public void setActualDetails(TransactionDetails actualDetails) {
        this.actualDetails = actualDetails;
    }
}
