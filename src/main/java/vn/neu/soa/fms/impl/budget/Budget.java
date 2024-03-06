package vn.neu.soa.fms.impl.budget;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import vn.neu.soa.fms.api.JsonParsable;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
public class Budget implements Serializable, JsonParsable {
    @Setter(value = AccessLevel.NONE)
    private int budgetId;
    private BudgetType type;
    private String name;
    private double expectedAmount;
    private double spentAmount;

    public static Budget deserialize(Map<String, Object> data) {
        return new Budget(
                Integer.parseInt((String) data.get("id")),
                BudgetType.parse((String) data.get("type")),
                (String) data.get("name"),
                Double.parseDouble((String) data.get("expected_amount")),
                Double.parseDouble((String) data.get("spent_amount"))
        );
    }

    @Override
    public JsonObject toJson() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", getBudgetId() + "");
        jsonObject.addProperty("type", getType().toString());
        jsonObject.addProperty("name", getName());
        jsonObject.addProperty("expected_amount", getExpectedAmount() + "");
        jsonObject.addProperty("spent_amount", getSpentAmount() + "");

        return jsonObject;
    }
}
