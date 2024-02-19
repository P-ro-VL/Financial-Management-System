package vn.neu.soa.fms.api.investment;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@Data
public abstract class AbstractInvestable implements Investable {

    @Setter(AccessLevel.NONE)
    private String ID;

    private String name;
    private Date investDate;
    private double investAmount;
    private double expectedProfitFactor;

    @Override
    public String toString() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", getID());
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("invest-date", new SimpleDateFormat("dd/MM/yyyy").format(getInvestDate()));
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
