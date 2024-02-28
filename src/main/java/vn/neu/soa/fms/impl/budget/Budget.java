package vn.neu.soa.fms.impl.budget;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Budget implements Serializable {
    @Setter(value = AccessLevel.NONE)
    private int budgetId;
    private BudgetType type;
    private String name;
    private double expectedAmount;
    private double spentAmount;
}
