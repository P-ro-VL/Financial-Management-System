package vn.neu.soa.fms.api.accounting;

public enum AccountingCategory {
    SALE_AND_SERVICES_REVENUE(1, "Doanh thu bán hàng và cung cấp dịch vụ"),
    REVENUE_DEDUCTION(2, "Các khoản giảm trừ doanh thu"),
    SALE_AND_SERVICES_NET_REVENUE(10, "Doanh thu thuần về bán hàng và cung cấp dịch vụ", "1 - 2"),
    COST_OF_SALES(11, "Giá vốn bán hàng"),
    SALE_AND_SERVICES_GROSS_PROFIT(20, "Lợi nhuận gộp về bán hàng và cung cấp dịch vụ", "10 - 11"),
    FINANCIAL_ACTIVITY_REVENUE(21, "Doanh thu hoạt động tài chính"),
    FINANCIAL_EXPENSES(22, "Chi phí tài chính"),
    INTEREST_EXPENSES(23, "Chi phí lãi vay"),
    BUSINESS_EXPENSES(24, "Chi phí quản lý kinh doanh"),
    BUSINESS_NET_PROFIT(30, "Lợi nhuận thuần từ hoạt động kinh doanh", "20 + 21 - 22 - 24"),
    OTHER_INCOME(31, "Thu nhập khác"),
    OTHER_EXPENSES(32, "Chi phí khác"),
    OTHER_PROFIT(40, "Lợi nhuận khác", "31 - 32"),
    TOTAL_ACCOUNTING_PROFIT_BEFORE_TAX(50, "Tổng lợi nhuận kế toán trước thuế", "30 + 40"),
    CORPERATE_INCOME_TAX_EXPENSES(51, "Chi phí thế TNDN"),
    PROFIT_AFTER_CITE(60, "Lợi nhuận sau thuế thu nhập doanh nghiệp", "50 - 51");

    public static AccountingCategory getById(int id) {
        for(AccountingCategory category : values()) {
            if(category.getStandardId() == id) return category;
        }
        return null;
    }

    AccountingCategory(int standardId, String displayName) {
        this.standardId = standardId;
        this.displayName = displayName;
    }

    AccountingCategory(int standardId, String displayName, String equation) {
        this.standardId = standardId;
        this.displayName = displayName;
        this.equation = equation;
    }

    private int standardId;
    private String displayName;

    private String equation = "";

    public static AccountingCategory parse(String category) {
        for(AccountingCategory cate : values()) {
            if(category.equalsIgnoreCase(cate.toString())) return cate;
        }
        throw new IllegalArgumentException("Cannot find category with name '" + category + "'.");
    }

    public int getStandardId() {
        return standardId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEquation() {
        return equation;
    }
}
