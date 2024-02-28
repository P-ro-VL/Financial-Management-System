package vn.neu.soa.fms.services;
import vn.neu.soa.fms.impl.budget.Budget;
import vn.neu.soa.fms.impl.budget.BudgetManager;

import javax.ejb.Stateless;

@Stateless
public class BudgetService {

    private BudgetManager entityManager = new BudgetManager();

    public BudgetManager getManager() {
        return entityManager;
    }

    //Thêm ngân sách mới
    public void addBudget(Budget budget) {
        entityManager.add(budget);
    }

    //Cập nhật ngân sách đã tồn tại
    public void updateBudget(int id, Budget newInfo) {
        Budget budget = getBudget(id);
        budget.setType(newInfo.getType());
        budget.setName(newInfo.getName());
        budget.setExpectedAmount(newInfo.getExpectedAmount());
        budget.setSpentAmount(newInfo.getSpentAmount());
    }

    public void deleteBudget(int budgetId) {
        entityManager.removeByID(budgetId + "");
    }

    public Budget getBudget(int budgetId) {
        return entityManager.getFirstWhere((budget) -> (budget.getBudgetId() == budgetId)).get();
    }
}
