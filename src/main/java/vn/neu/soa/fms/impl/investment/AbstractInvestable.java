package vn.neu.soa.fms.impl.investment;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.glassfish.jaxb.runtime.v2.runtime.reflect.opt.Const;
import vn.neu.soa.fms.api.investment.Investable;
import vn.neu.soa.fms.utils.Constants;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

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

    public static AbstractInvestable deserialize(Map<String, Object> data) throws ParseException {
        return new AbstractInvestable(
                (String) data.get("id"),
                (String) data.get("name"),
                Constants.STANDARD_DATE_FORMAT.parse((String) data.get("invest-date")),
                Constants.STANDARD_DATE_FORMAT.parse((String) data.get("end-date")),
                Double.parseDouble((String) data.get("invest-amount")),
                Double.parseDouble((String) data.get("expected-profit-factor"))
        ) {
            @Override
            public boolean invest() {
                return false;
            }
        };
    }

    @Override
    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("id", ID);
        object.addProperty("name", name);
        object.addProperty("invest-date", Constants.STANDARD_DATE_FORMAT.format(investDate));
        object.addProperty("end-date", Constants.STANDARD_DATE_FORMAT.format(endDate));
        object.addProperty("invest-amount", investAmount);
        object.addProperty("expected-profit-factor", expectedProfitFactor);

        return object;
    }

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
