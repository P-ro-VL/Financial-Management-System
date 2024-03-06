package vn.neu.soa.fms.services;

import jakarta.ejb.Stateful;
import lombok.Getter;
import vn.neu.soa.fms.api.investment.Investable;
import vn.neu.soa.fms.impl.investment.AbstractInvestable;
import vn.neu.soa.fms.impl.investment.InvestmentManager;

import java.util.List;

@Stateful
public class InvestmentService implements InvestmentServiceLocal {

    @Getter
    private static InvestmentManager manager = new InvestmentManager();

    @Override
    public void addInvestment(Investable investable) {
        manager.add((AbstractInvestable) investable);
    }

    @Override
    public AbstractInvestable getInvestable(String id) {
        return manager.getFirstWhere((invest) -> invest.getID().equalsIgnoreCase(id)).get();
    }

    @Override
    public List<AbstractInvestable> getAll() {
        return manager.getAll();
    }
}
