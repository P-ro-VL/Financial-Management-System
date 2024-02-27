package vn.neu.soa.fms.services;

import vn.neu.soa.fms.impl.audit.*;

import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.inject.Inject;

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