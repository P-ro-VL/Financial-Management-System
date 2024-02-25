package vn.neu.soa.fms.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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

public class Account {
    private int id;
    public int getId() {
        return id;
    }
}

public class AuditFinding {
    private AuditFindingType type;
    private Account account;
    private BigDecimal expectedBalance;
    private BigDecimal actualBalance;
    private Transaction transaction;
    private TransactionDetails expectedDetails;
    private TransactionDetails actualDetails;

    // Getters và setters cho các thuộc tính

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

public enum AuditFindingType {
    ACCOUNT_BALANCE_INACCURATE,
    TRANSACTION_INVALID
}

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
public interface FinancialManagementSystemEJB {
    BigDecimal getAccountBalance(int accountId);
    TransactionDetails getTransactionDetails(int transactionId);
}

public class FinancialManagementSystemEJBImpl implements FinancialManagementSystemEJB {
    @Override
    public BigDecimal getAccountBalance(int accountId) {
        return BigDecimal.ZERO;
    }

    @Override
    public TransactionDetails getTransactionDetails(int transactionId) {
        return new TransactionDetails();
    }
}

public enum AuditOpinion {
    FAIR,
    QUALIFIED
}

public interface FinancialStatementAuditServiceLocal {
    void auditFinancialStatements(FinancialStatement financialStatement);
}
@Stateless
public class FinancialStatementAuditService {

    @Inject
    private FinancialManagementSystemEJB financialManagementSystemEJB;

    public void auditFinancialStatements(FinancialStatement financialStatement) {
        // Kiểm tra tính chính xác của số dư tài khoản
        for (Account account : financialStatement.getAccounts()) {
            // Truy cập số dư tài khoản từ hệ thống quản lý tài chính
            BigDecimal balance = financialManagementSystemEJB.getAccountBalance(account.getId());

            // So sánh số dư tài khoản với số dư được ghi trong báo cáo tài chính
            if (balance.compareTo(financialStatement.getBalance(account.getId())) != 0) {
                // Ghi lại sai sót
                AuditFinding finding = new AuditFinding();
                finding.setType(AuditFindingType.ACCOUNT_BALANCE_INACCURATE);
                finding.setAccount(account);
                finding.setExpectedBalance(balance);
                finding.setActualBalance(financialStatement.getBalance(account.getId()));

                // Lưu trữ phát hiện
                financialStatement.addAuditFinding(finding);
            }
        }

        // Kiểm tra tính hợp lệ của các giao dịch
        for (Transaction transaction : financialStatement.getTransactions()) {
            // Truy cập chi tiết giao dịch từ hệ thống quản lý tài chính
            TransactionDetails details = financialManagementSystemEJB.getTransactionDetails(transaction.getId());

            // So sánh chi tiết giao dịch với thông tin được ghi trong báo cáo tài chính
            if (!details.getDate().equals(transaction.getDate())
                    || !details.getAmount().equals(transaction.getAmount())
                    || !details.getDescription().equals(transaction.getDescription())) {
                // Ghi lại sai sót
                AuditFinding finding = new AuditFinding();
                finding.setType(AuditFindingType.TRANSACTION_INVALID);
                finding.setTransaction(transaction);
                finding.setExpectedDetails(details);
                finding.setActualDetails(transaction.getDetails());

                // Lưu trữ phát hiện
                financialStatement.addAuditFinding(finding);
            }
        }

        // Cung cấp ý kiến độc lập về tình hình tài chính của doanh nghiệp
        if (financialStatement.getAuditFindings().isEmpty()) {
            financialStatement.setAuditOpinion(AuditOpinion.FAIR);
        } else {
            financialStatement.setAuditOpinion(AuditOpinion.QUALIFIED);
        }
    }
}