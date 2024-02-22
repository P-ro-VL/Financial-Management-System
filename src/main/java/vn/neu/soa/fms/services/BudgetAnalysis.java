package vn.neu.soa.fms.services;

import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class BudgetAnalysis {

    // Phương thức tính toán mức độ thực hiện theo ngân sách và trả về tỷ lệ phần trăm
    public double calculateBudgetCompletion(double budgetForecast, double actualExpenses) {
        return (actualExpenses / budgetForecast) * 100;
    }

    // Phương thức lập biểu đồ
    public void drawChart(double budgetForecast, double actualExpenses) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Budget Forecast", budgetForecast);
        dataset.setValue("Actual Expenses", actualExpenses);

        JFreeChart chart = ChartFactory.createPieChart(
                "Budget Analysis",
                dataset,
                true, // include legend
                true,
                false);

        // Hiển thị biểu đồ trong cửa sổ
        JFrame frame = new JFrame("Budget Analysis Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    // Phương thức lập biểu đồ và tạo bảng phân tích
    public void analyzeBudget() {
        double budgetForecast = getBudgetForecastFromAccountingService();
        double actualExpenses = getActualExpensesFromAccountingService();

        drawChart(budgetForecast, actualExpenses);
        generateAnalysisTable(budgetForecast, actualExpenses);
    }
    }
    private class ChartPanel extends Component{
    public ChartPanel(JFreeChart chart) {

    }

    // Phương thức tạo bảng phân tích
    public void generateAnalysisTable(double budgetForecast, double actualExpenses) {
        System.out.println("Budget Analysis:");
        System.out.println("------------------------------");
        System.out.println("Budget Forecast: $" + budgetForecast);
        System.out.println("Actual Expenses: $" + actualExpenses);
        System.out.println("Budget Completion: " + calculateBudgetCompletion(budgetForecast, actualExpenses) + "%");
    }

    // Phương thức lấy dữ liệu dự trù từ dịch vụ kế toán
    public double getBudgetForecastFromAccountingService() {
        // Code để gọi dịch vụ kế toán và lấy dữ liệu dự trù
        // Ví dụ:
        // return accountingService.getBudgetForecast();
        return 10000; // Giả sử dữ liệu dự trù là $10,000
    }

    // Phương thức lấy dữ liệu thực tế từ dịch vụ kế toán
    public double getActualExpensesFromAccountingService() {
        // Code để gọi dịch vụ kế toán và lấy dữ liệu thực tế
        // Ví dụ:
        // return accountingService.getActualExpenses();
        return 8000; // Giả sử dữ liệu thực tế là $8,000
    }

    public static void main(String[] args) {
        BudgetAnalysis budgetAnalysis = new BudgetAnalysis();

        // Lấy dữ liệu dự trù và thực tế từ dịch vụ kế toán
        double budgetForecast = budgetAnalysis.getBudgetForecastFromAccountingService();
        double actualExpenses = budgetAnalysis.getActualExpensesFromAccountingService();

        // Lập biểu đồ và tạo bảng phân tích
        budgetAnalysis.drawChart(budgetForecast, actualExpenses);
        budgetAnalysis.generateAnalysisTable(budgetForecast, actualExpenses);
    }
}

