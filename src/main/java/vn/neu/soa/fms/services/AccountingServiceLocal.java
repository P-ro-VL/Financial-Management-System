package vn.neu.soa.fms.services;

import jakarta.ejb.Local;
import vn.neu.soa.fms.impl.accounting.AccountingBook;
import vn.neu.soa.fms.impl.accounting.AccountingRecord;

@Local
public interface AccountingServiceLocal {

    public void addRecord(AccountingRecord record);
    public AccountingRecord getRecord(int id);
    public AccountingBook getBook();
}
