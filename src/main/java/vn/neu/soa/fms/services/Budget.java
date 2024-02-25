package vn.neu.soa.fms.services;
import java.io.Serializable;

public class Budget implements Serializable {
    private int budgetId;
    private String category;
    private double amount;

    public Budget(int budgetId, String category, double amount) {
        this.budgetId = budgetId;
        this.category = category;
        this.amount = amount;
    }

    // Các phương thức getter và setter

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
