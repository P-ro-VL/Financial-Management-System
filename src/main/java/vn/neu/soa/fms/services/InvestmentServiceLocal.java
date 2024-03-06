package vn.neu.soa.fms.services;

import jakarta.ejb.Local;
import vn.neu.soa.fms.api.investment.Investable;
import vn.neu.soa.fms.impl.investment.AbstractInvestable;

import java.util.List;

@Local
public interface InvestmentServiceLocal {

    public void addInvestment(Investable investable);
    public AbstractInvestable getInvestable(String id);
    public List<AbstractInvestable> getAll();
}
