package vn.neu.soa.fms.services;
import jakarta.ejb.Stateful;
import jakarta.ejb.Stateless;
import lombok.Getter;
import vn.neu.soa.fms.impl.budget.Budget;
import vn.neu.soa.fms.impl.budget.BudgetManager;
import vn.neu.soa.fms.impl.budget.BudgetType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Stateful
public class BudgetService implements BudgetServiceLocal {

    @Getter
    private static BudgetManager budgetManager = new BudgetManager();

    @Override
    public Budget getBudget(int id) {
        return budgetManager.getFirstWhere((budget) -> budget.getBudgetId() == id).get();
    }

    @Override
    public List<Budget> getBudgets(BudgetType type) {
        return new ArrayList<>(budgetManager.getWhere((budget) -> budget.getType() == type));
    }

    @Override
    public void addBudget(Budget budget) {
        budgetManager.add(budget);
    }

    @Override
    public List<Budget> getAll() {
        return budgetManager.getAll();
    }
}
