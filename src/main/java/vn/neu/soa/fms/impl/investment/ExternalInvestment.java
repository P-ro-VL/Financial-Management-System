package vn.neu.soa.fms.impl.investment;

import vn.neu.soa.fms.api.investment.AbstractInvestable;

import java.util.Date;

public class ExternalInvestment extends AbstractInvestable {
    public ExternalInvestment(String ID, String name, Date investDate, double investAmount, double expectedProfitFactor) {
        super("external-" + ID, name, investDate, investAmount, expectedProfitFactor);
    }

    @Override
    public boolean invest() {
        return false;
    }
}
