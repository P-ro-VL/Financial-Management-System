package vn.neu.soa.fms.impl.audit;

import java.math.BigDecimal;

public interface FinancialManagementSystemEJB {
    BigDecimal getAccountBalance(int accountId);

    TransactionDetails getTransactionDetails(int transactionId);
}
