package vn.neu.soa.fms.api.investment;

public interface Investable {

    public boolean invest();

    public double calculateExpectedProfit();

    public double calculateTargetRevenue();

}
