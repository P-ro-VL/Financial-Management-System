package vn.neu.soa.fms.services;

import javax.ejb.Stateless;
// Giả sử ở đây chúng ta chỉ trả về ngân sách mới là giá trị trung bình của ngân sách hiện tại và ngân sách mong muốn
@Stateless
public class BudgetManagement implements BudgetOptimization {
    public double optimizeBudget(double currentBudget, double desiredBudget) {


        double optimizedBudget = (currentBudget + desiredBudget) / 2;

        return optimizedBudget;
    }
}
