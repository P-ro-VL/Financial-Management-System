package vn.neu.soa.fms.services;

import jakarta.ejb.Stateful;
import lombok.Getter;
import vn.neu.soa.fms.impl.accounting.AccountingBook;
import vn.neu.soa.fms.impl.accounting.AccountingRecord;

@Stateful
public class AccountingService implements AccountingServiceLocal {

    @Getter
    public static AccountingBook manager = new AccountingBook();

    @Override
    public AccountingBook getBook() {
        return manager;
    }

    @Override
    public void addRecord(AccountingRecord record) {
        manager.add(record);
    }

    public void updateAccountingRecord(int id, AccountingRecord newInfo) {
        AccountingRecord book = getRecord(id);
        book.setDate(newInfo.getDate());
        book.setReferenceCode(newInfo.getReferenceCode());
        book.setDescription(newInfo.getDescription());
        book.setDebit(newInfo.getDebit());
        book.setCredit(newInfo.getCredit());
    }

    public void deleteAccountingRecord(int bookId) {
        manager.removeByID(bookId + "");
        AccountingRecord book = getRecord(bookId);
    }

    @Override
    public AccountingRecord getRecord(int recordId) {
        return manager.getFirstWhere((record) -> (record.getId() == recordId)).get();
    }
}
