package vn.neu.soa.fms.services;


import vn.neu.soa.fms.api.Manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

    public class AccountingBookManager implements Manager<AccountingBook> {

        private List<AccountingBook> books = new ArrayList<>();

        @Override
        public void add(AccountingBook object) {
            books.add(object);
        }

        @Override
        public boolean has(AccountingBook object) {
            return getFirstWhere((book) -> book.getId() == object.getId()).isPresent();
        }

        @Override
        public boolean has(String ID) {
            return getFirstWhere((book) -> (book.getId() + "").equalsIgnoreCase(ID)).isPresent();
        }

        @Override
        public void remove(AccountingBook object) {
            books.remove(object);
        }

        @Override
        public void removeByID(String ID) {
            books.removeIf((book) -> (book.getId() + "").equalsIgnoreCase(ID));
        }

        @Override
        public List<AccountingBook> getAll() {
            return books;
        }

        @Override
        public List<AccountingBook> getWhere(Predicate<AccountingBook> predicate) {
            return books.stream().filter(predicate).collect(Collectors.toList());
        }

        @Override
        public Optional<AccountingBook> getFirstWhere(Predicate<AccountingBook> predicate) {
            return books.stream().filter(predicate).findFirst();
        }
    }


