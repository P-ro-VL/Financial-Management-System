package vn.neu.soa.fms.impl.audit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FinancialStatement {
    private List<Account> accounts;
    private List<Transaction> transactions;
    private List<AuditFinding> auditFindings;
    private AuditOpinion auditOpinion;

    public FinancialStatement() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
        auditFindings = new ArrayList<>();
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public BigDecimal getBalance(int accountId) {
        return BigDecimal.ZERO;
    }

    public void addAuditFinding(AuditFinding finding) {
        auditFindings.add(finding);
    }

    public List<AuditFinding> getAuditFindings() {
        return auditFindings;
    }

    public void setAuditOpinion(AuditOpinion opinion) {
        auditOpinion = opinion;
    }
}
