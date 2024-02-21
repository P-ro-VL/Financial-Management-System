package vn.neu.soa.fms.impl.investment;

import vn.neu.soa.fms.api.Manager;
import vn.neu.soa.fms.api.investment.AbstractInvestable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvestmentManager implements Manager<AbstractInvestable> {

    List<AbstractInvestable> investables = new ArrayList<>();

    @Override
    public void add(AbstractInvestable object) {
        investables.add(object);
    }

    @Override
    public boolean has(AbstractInvestable object) {
        return investables.contains(object);
    }

    @Override
    public boolean has(String ID) {
        return getFirstWhere((investable) -> investable.getID().equalsIgnoreCase(ID)).isPresent();
    }

    @Override
    public void remove(AbstractInvestable object) {
        investables.remove(object);
    }

    @Override
    public void removeByID(String ID) {
        investables.removeIf((investable) -> investable.getID().equalsIgnoreCase(ID));
    }

    @Override
    public List<AbstractInvestable> getAll() {
        return investables;
    }

    @Override
    public List<AbstractInvestable> getWhere(Predicate<AbstractInvestable> predicate) {
        return investables.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<AbstractInvestable> getFirstWhere(Predicate<AbstractInvestable> predicate) {
        return investables.stream().filter(predicate).findFirst();
    }
}
