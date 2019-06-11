package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.WeighedBatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IWeighedBatchDAO {
    WeighedBatch createweighedBatch();

    WeighedBatch getObject(Connection conn, int weighedBatchId) throws NotFoundException, SQLException;

    void load(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, WeighedBatch weighedBatch) throws SQLException;

    void save(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, WeighedBatch weighedBatch) throws SQLException;
}
