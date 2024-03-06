package vn.neu.soa.fms.impl.audit;

import vn.neu.soa.fms.api.audit.AbstractAudit;
import vn.neu.soa.fms.impl.accounting.FinancialReport;

public class InternalAudit extends AbstractAudit {

    public InternalAudit(FinancialReport report, AuditFindingType type) {
        super(type);

        this.report = report;
    }

    private FinancialReport report;

    @Override
    public AuditResult audit() {
        return null;
    }
}
