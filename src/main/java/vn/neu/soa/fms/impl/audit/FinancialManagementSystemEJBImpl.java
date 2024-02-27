package vn.neu.soa.fms.impl.audit;

import java.math.BigDecimal;

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
