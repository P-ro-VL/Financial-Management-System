package vn.neu.soa.fms.impl.accounting;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import vn.neu.soa.fms.api.JsonParsable;
import vn.neu.soa.fms.api.accounting.AccountingCategory;
import vn.neu.soa.fms.utils.Constants;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class AccountingRecord implements Serializable, JsonParsable {
    private int id;
    private Date date;
    private String referenceCode;
    private String name;
    private String description;
    private double debit;
    private double credit;
    private AccountingCategory category;

    public static AccountingRecord deserialize(Map<String, Object> objectMap) throws ParseException {
        return new AccountingRecord(
                Integer.parseInt((String) objectMap.get("id")),
                Constants.STANDARD_TIMESTAMP_FORMAT.parse((String) objectMap.get("date")),
                (String) objectMap.get("code"),
                (String) objectMap.get("name"),
                (String) objectMap.get("description"),
                Double.parseDouble((String)objectMap.get("debit")),
                Double.parseDouble((String)objectMap.get("credit")),
                AccountingCategory.parse(((String) objectMap.get("category")).toUpperCase())
        );
    }

    public JsonObject toJson() {
        JsonObject object = new JsonObject();
        object.addProperty("id", id);
        object.addProperty("date", Constants.STANDARD_TIMESTAMP_FORMAT.format(date));
        object.addProperty("code", referenceCode);
        object.addProperty("name", name);
        object.addProperty("description", description);
        object.addProperty("debit", debit + "");
        object.addProperty("credit", credit + "");
        object.addProperty("category", category.toString());

        return object;
    }
}
