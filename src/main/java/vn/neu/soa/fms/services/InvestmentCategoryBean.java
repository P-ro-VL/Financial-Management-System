package vn.neu.soa.fms.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.util.List;
import java.util.Date;

public class InvestmentCategoryBean {
    private EntityManager entityManager;

    public void addInvestmentCategory(String investmentCode, String category, Date investmentDate, double investmentAmount, double desiredProfit) {
        InvestmentCategory investmentCategory = new InvestmentCategory();
        investmentCategory.setInvestmentCode(investmentCode);
        investmentCategory.setCategory(category);
        investmentCategory.setInvestmentDate(investmentDate);
        investmentCategory.setInvestmentAmount(investmentAmount);
        investmentCategory.setDesiredProfit(desiredProfit);
        entityManager.persist(investmentCategory);
    }
    public void updateInvestmentCategory(String investmentCode, String category, Date investmentDate, double investmentAmount, double desiredProfit) {
        InvestmentCategory investmentCategory = entityManager.find(InvestCategory.class, investmentCode);
        if (investmentCategory != null) {
            investmentCategory.setCategory(category);
            investmentCategory.setInvestmentDate(investmentDate);
            investmentCategory.setInvestmentAmount(investmentAmount);
            investmentCategory.setDesiredProfit(desiredProfit);
            entityManager.merge(investmentCategory);
        }
    }
    
}