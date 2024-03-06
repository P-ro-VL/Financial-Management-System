package vn.neu.soa.fms.services;

import jakarta.ejb.Local;
import vn.neu.soa.fms.impl.budget.Budget;
import vn.neu.soa.fms.impl.budget.BudgetType;

import java.util.List;

@Local
public interface BudgetServiceLocal {
    public Budget getBudget(int id);
    public List<Budget> getBudgets(BudgetType type);

    public void addBudget(Budget budget);
    public List<Budget> getAll();
}
