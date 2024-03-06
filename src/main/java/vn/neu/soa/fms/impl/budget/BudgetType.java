package vn.neu.soa.fms.impl.budget;

public enum BudgetType {
    HUMAN_RESOURCES,
    TECHNOLOGY,
    R_AND_D,
    MARKETING,
    OPERATION,
    INVESTMENT;

    public static BudgetType parse(String type) {
        for(BudgetType budgetType : values()) {
            if(budgetType.toString().equalsIgnoreCase(type)) return budgetType;
        }
        throw new IllegalArgumentException("There is no BudgetType whose name is '" + type + "'");
    }
}
