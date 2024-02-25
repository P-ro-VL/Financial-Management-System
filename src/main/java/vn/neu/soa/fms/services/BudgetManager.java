package vn.neu.soa.fms.services;

import vn.neu.soa.fms.api.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BudgetManager implements Manager<Budget> {

    private List<Budget> budgets = new ArrayList<>();

    @Override
    public void add(Budget object) {
        budgets.add(object);
    }

    @Override
    public boolean has(Budget object) {
        return getFirstWhere((budget) -> budget.getBudgetId() == object.getBudgetId()).isPresent();
    }

    @Override
    public boolean has(String ID) {
        return getFirstWhere((budget) -> (budget.getBudgetId() + "").equalsIgnoreCase(ID)).isPresent();
    }

    @Override
    public void remove(Budget object) {
        budgets.remove(object);
    }

    @Override
    public void removeByID(String ID) {
        budgets.removeIf((budget) -> (budget.getBudgetId() + "").equalsIgnoreCase(ID));
    }

    @Override
    public List<Budget> getAll() {
        return budgets;
    }

    @Override
    public List<Budget> getWhere(Predicate<Budget> predicate) {
        return budgets.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<Budget> getFirstWhere(Predicate<Budget> predicate) {
        return budgets.stream().filter(predicate).findFirst();
    }
}

