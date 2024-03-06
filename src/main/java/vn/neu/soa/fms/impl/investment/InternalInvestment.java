package vn.neu.soa.fms.impl.investment;

import java.util.Date;

public class InternalInvestment extends AbstractInvestable {
    public InternalInvestment(String ID, String name, Date investDate, Date endDate, double investAmount, double expectedProfitFactor) {
        super("internal-" + ID, name, investDate, endDate, investAmount, expectedProfitFactor);
    }

    @Override
    public boolean invest() {
        return false;
    }
}
