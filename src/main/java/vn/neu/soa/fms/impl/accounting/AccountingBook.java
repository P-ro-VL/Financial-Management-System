package vn.neu.soa.fms.impl.accounting;


import vn.neu.soa.fms.api.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AccountingBook implements Manager<AccountingRecord> {

    private List<AccountingRecord> records = new ArrayList<>();

    @Override
    public void add(AccountingRecord object) {
        records.add(object);
    }

    @Override
    public boolean has(AccountingRecord object) {
        return getFirstWhere((book) -> book.getId() == object.getId()).isPresent();
    }

    @Override
    public boolean has(String ID) {
        return getFirstWhere((book) -> (book.getId() + "").equalsIgnoreCase(ID)).isPresent();
    }

    @Override
    public void remove(AccountingRecord object) {
        records.remove(object);
    }

    @Override
    public void removeByID(String ID) {
        records.removeIf((book) -> (book.getId() + "").equalsIgnoreCase(ID));
    }

    @Override
    public List<AccountingRecord> getAll() {
        return records;
    }

    @Override
    public List<AccountingRecord> getWhere(Predicate<AccountingRecord> predicate) {
        return records.stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountingRecord> getFirstWhere(Predicate<AccountingRecord> predicate) {
        return records.stream().filter(predicate).findFirst();
    }
}


