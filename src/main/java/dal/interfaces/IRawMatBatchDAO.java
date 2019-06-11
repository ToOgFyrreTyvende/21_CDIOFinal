package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.RawMatBatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRawMatBatchDAO {
    RawMatBatch createrawMatBatch();

    RawMatBatch getObject(Connection conn, int rmbId) throws NotFoundException, SQLException;

    void load(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, RawMatBatch rawMatBatch) throws SQLException;

    void save(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, RawMatBatch rawMatBatch) throws SQLException;

    String getDaogenVersion();
}
