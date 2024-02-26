package vn.neu.soa.fms.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
public class AccountingService {
    @PersistenceContext
    private EntityManager entityManager;

    private AccountingBookManager manager = new AccountingBookManager();

    public AccountingBookManager getManager() {
        return manager;
    }

    // Thêm mục sổ kế toán mới
    public void addAccountingBook(AccountingBook book) {
        manager.add(book);
        entityManager.persist(book); // Lưu vào database
    }

    // Cập nhật mục sổ kế toán đã tồn tại
    public void updateAccountingBook(int id, AccountingBook newInfo) {
        AccountingBook book = getAccountingBook(id);
        book.setDate(newInfo.getDate());
        book.setReferenceCode(newInfo.getReferenceCode());
        book.setDescription(newInfo.getDescription());
        book.setDebit(newInfo.getDebit());
        book.setCredit(newInfo.getCredit());
        entityManager.merge(book); // Cập nhật vào database
    }

    // Xóa một mục sổ kế toán
    public void deleteAccountingBook(int bookId) {
        manager.removeByID(bookId + "");
        AccountingBook book = getAccountingBook(bookId);
        entityManager.remove(book); // Xóa khỏi database
    }

    // Truy vấn thông tin của một mục sổ kế toán
    public AccountingBook getAccountingBook(int bookId) {
        return manager.getFirstWhere((book) -> (book.getId() == bookId)).get();
    }
}
