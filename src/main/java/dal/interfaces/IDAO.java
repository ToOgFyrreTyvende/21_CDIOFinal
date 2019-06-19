package dal.interfaces;

import dal.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T getObject(Connection conn, int prodBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, T ProductBatch) throws NotFoundException, SQLException;

    List<T> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, T ProductBatch) throws SQLException;

    void save(Connection conn, T ProductBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, T ProductBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<T> searchMatching(Connection conn, T ProductBatch) throws SQLException;

}
