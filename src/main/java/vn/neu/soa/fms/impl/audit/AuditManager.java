package vn.neu.soa.fms.impl.audit;

import vn.neu.soa.fms.api.Manager;
import vn.neu.soa.fms.api.audit.AbstractAudit;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AuditManager implements Manager<AbstractAudit> {

    private List<AbstractAudit> auditHistory = new ArrayList<>();

    @Override
    public void add(AbstractAudit object) {
        auditHistory.add(object);
    }

    @Override
    public boolean has(AbstractAudit object) {
        return auditHistory.contains(object);
    }

    @Override
    public boolean has(String ID) {
        return getFirstWhere((audit) -> audit.getID().equalsIgnoreCase(ID)).isPresent();
    }

    @Override
    public void remove(AbstractAudit object) {
        auditHistory.remove(object);
    }

    @Override
    public void removeByID(String ID) {
        auditHistory.removeIf((audit) -> audit.getID().equalsIgnoreCase(ID));
    }

    @Override
    public List<AbstractAudit> getAll() {
        return auditHistory;
    }

    @Override
    public List<AbstractAudit> getWhere(Predicate<AbstractAudit> predicate) {
        return auditHistory.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<AbstractAudit> getFirstWhere(Predicate<AbstractAudit> predicate) {
        return auditHistory.stream().filter(predicate).findFirst();
    }
}
