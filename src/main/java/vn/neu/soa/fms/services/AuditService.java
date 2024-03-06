package vn.neu.soa.fms.services;

import jakarta.ejb.Stateful;
import vn.neu.soa.fms.api.audit.IAudit;
import vn.neu.soa.fms.impl.audit.AuditManager;
import vn.neu.soa.fms.impl.audit.AuditResult;

@Stateful
public class AuditService {

    private AuditManager auditManager = new AuditManager();

    public AuditManager getAuditManager() {
        return auditManager;
    }

    public AuditResult audit(IAudit audit) {
        return audit.audit();
    }
}
