package vn.neu.soa.fms.api.audit;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import vn.neu.soa.fms.impl.audit.AuditFindingType;

import java.util.Date;

@Data
public abstract class AbstractAudit implements IAudit {

    @Setter(value = AccessLevel.NONE)
    private String ID;
    private Date date;
    private AuditFindingType type;

    public AbstractAudit(AuditFindingType type) {
        this.type = type;
    }

}
