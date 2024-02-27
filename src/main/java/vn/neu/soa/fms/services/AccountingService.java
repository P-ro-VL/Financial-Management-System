package vn.neu.soa.fms.services;

import vn.neu.soa.fms.impl.accounting.AccountingBook;
import vn.neu.soa.fms.impl.accounting.AccountingRecord;

import javax.ejb.Stateful;

@Stateful
public class AccountingService {
    private AccountingBook manager = new AccountingBook();

    public AccountingBook getManager() {
        return manager;
    }

    public void addAccountingRecord(AccountingRecord book) {
        manager.add(book);
    }

    public void updateAccountingRecord(int id, AccountingRecord newInfo) {
        AccountingRecord book = getAccountingRecord(id);
        book.setDate(newInfo.getDate());
        book.setReferenceCode(newInfo.getReferenceCode());
        book.setDescription(newInfo.getDescription());
        book.setDebit(newInfo.getDebit());
        book.setCredit(newInfo.getCredit());
    }

    public void deleteAccountingRecord(int bookId) {
        manager.removeByID(bookId + "");
        AccountingRecord book = getAccountingRecord(bookId);
    }

    public AccountingRecord getAccountingRecord(int bookId) {
        return manager.getFirstWhere((book) -> (book.getId() == bookId)).get();
    }
}
