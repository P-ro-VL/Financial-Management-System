package vn.neu.soa.fms.services;

import vn.neu.soa.fms.impl.investment.InvestmentManager;

import javax.ejb.Stateful;

@Stateful
public class InvestmentService {

    private InvestmentManager manager = new InvestmentManager();

    public InvestmentManager getManager() {
        return manager;
    }

}
