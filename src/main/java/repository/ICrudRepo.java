package repository;

import java.sql.SQLException;
import java.util.List;

public interface ICrudRepo<T> {
    T find(long Id) throws SQLException;

    List<T> getAll() throws SQLException;

    T update(T obj) throws SQLException;

    void delete(long Id) throws SQLException;
}
