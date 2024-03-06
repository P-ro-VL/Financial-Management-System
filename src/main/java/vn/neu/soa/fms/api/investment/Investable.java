package vn.neu.soa.fms.api.investment;

import vn.neu.soa.fms.api.JsonParsable;

public interface Investable extends JsonParsable {

    public boolean invest();

    public double calculateExpectedProfit();

    public double calculateTargetRevenue();

}
