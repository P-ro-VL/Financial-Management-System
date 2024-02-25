package vn.neu.soa.fms;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import vn.neu.soa.fms.services.Budget;
import vn.neu.soa.fms.services.BudgetManagerBean;

public class Test extends TestCase {

    public static TestResult test() {
        return TestRunner.run(new TestSuite(TestCases.class));
    }

    public static class TestCases extends TestCase{

        public void testAddBudget() {
            Budget budget= new Budget(1, "Test", 100);

            BudgetManagerBean bean = new BudgetManagerBean();
            bean.addBudget(budget);

            assertEquals(bean.getBudget(1) != null, true);
        }

        public void testRemoveBudget() {
            Budget budget= new Budget(1, "Test", 100);

            BudgetManagerBean bean = new BudgetManagerBean();
            bean.addBudget(budget);

            bean.deleteBudget(1);

            assertEquals(bean.getManager().has("1"), false);
        }

        public void testUpdateBudget() {
            Budget budget= new Budget(1, "Test", 100);

            BudgetManagerBean bean = new BudgetManagerBean();
            bean.addBudget(budget);

            bean.updateBudget(1, new Budget(1, "Test", 200));

            assertEquals(bean.getBudget(1).getAmount(), 200.0);
        }
    }
}
