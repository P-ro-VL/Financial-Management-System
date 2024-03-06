package vn.neu.soa.fms.impl.accounting;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.mariuszgromada.math.mxparser.Expression;
import vn.neu.soa.fms.api.Report;
import vn.neu.soa.fms.api.accounting.AccountingCategory;
import vn.neu.soa.fms.utils.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class FinancialReport implements Report {

    @Setter(value = AccessLevel.NONE)
    private List<AccountingRecord> accountingRecords;

    @Override
    public JsonObject createReport() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "BÁO CÁO KẾT QUẢ HOẠT ĐỘNG KINH DOANH");
        jsonObject.addProperty("tax_number", "");
        jsonObject.addProperty("taxpayer", "");
        jsonObject.addProperty("created_time", Constants.STANDARD_TIMESTAMP_FORMAT.format(new Date()));

        JsonObject content = new JsonObject();

        Map<AccountingCategory, Double> summing = accountingRecords.stream().collect(Collectors.groupingBy(AccountingRecord::getCategory,
                Collectors.summingDouble((record) -> record.getCredit() + record.getDebit())));

        for (AccountingCategory category : AccountingCategory.values()) {
            JsonObject data = new JsonObject();

            data.addProperty("id", category.toString());
            data.addProperty("name", category.getDisplayName());
            if (category.getEquation().isEmpty()) {
                data.addProperty("amount", summing.get(category));
            } else {
                String parsedEquation = category.getEquation();
                List<Integer> nums = findNumbersInString(parsedEquation);
                for (int n : nums)
                    parsedEquation = parsedEquation.replaceAll(n + "",
                            String.valueOf(summing.get(AccountingCategory.getById(n))));

                Expression expression = new Expression(parsedEquation);
                double result = expression.calculate();
                data.addProperty("amount", result);
                data.addProperty("expression", category.getEquation());

                summing.put(category, result);
            }

            content.add(category.getStandardId() + "", data);
        }

        jsonObject.add("content", content);

        return jsonObject;
    }

    private List<Integer> findNumbersInString(String s) {
        List<Integer> nums = new ArrayList<>();

        String[] args = s.split(" ");
        for (String str : args) {
            try {
                int value = Integer.parseInt(str);
                nums.add(value);
            } catch (Exception ex) {
            }
        }

        return nums;
    }
}
