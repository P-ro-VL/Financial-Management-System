package vn.neu.soa.fms.api.investment;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import vn.neu.soa.fms.utils.Constants;

import java.util.Date;

@AllArgsConstructor
@Data
public abstract class AbstractInvestable implements Investable {

    @Setter(AccessLevel.NONE)
    private String ID;

    private String name;
    private Date investDate;
    private Date endDate;
    private double investAmount;
    private double expectedProfitFactor;

    @Override
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", getID());
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("invest-date", Constants.STANDARD_DATE_FORMAT.format(getInvestDate()));
        jsonObject.addProperty("invest-date-end", Constants.STANDARD_DATE_FORMAT.format(getEndDate()));
        jsonObject.addProperty("invest-amount", getInvestAmount());
        jsonObject.addProperty("expected-profit-factor", this.getExpectedProfitFactor());
        return jsonObject.getAsString();
    }

    @Override
    public double calculateExpectedProfit() {
        return getExpectedProfitFactor() * getInvestAmount();
    }

    @Override
    public double calculateTargetRevenue() {
        return calculateExpectedProfit() + getInvestAmount();
    }
}
