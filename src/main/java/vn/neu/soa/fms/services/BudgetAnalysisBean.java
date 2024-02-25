package vn.neu.soa.fms.services;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class BudgetChart {

    public void createBudgetChart(double plannedBudget, double actualBudget) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(plannedBudget, "Planned", "Budget");
        dataset.addValue(actualBudget, "Actual", "Budget");

        JFreeChart chart = ChartFactory.createBarChart(
                "Budget Analysis Chart",
                "Budget Type",
                "Amount",
                dataset
        );

        JFrame frame = new JFrame("Budget Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }
}
