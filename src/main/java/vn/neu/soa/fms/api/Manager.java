package vn.neu.soa.fms.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Manager<T> {

    public void add(T object);

    public boolean has(T object);
    public boolean has(String ID);

    public void remove(T object);

    public void removeByID(String ID);

    public List<T> getAll();

    public List<T> getWhere(Predicate<T> predicate);

    public Optional<T> getFirstWhere(Predicate<T> predicate);
}
