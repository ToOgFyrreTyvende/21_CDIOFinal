package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.IRawMatBatch;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRawMatBatchDAO {
    IRawMatBatch createrawMatBatch();

    IRawMatBatch getObject(Connection conn, int rmbId) throws NotFoundException, SQLException;

    void load(Connection conn, IRawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    List<IRawMatBatch> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IRawMatBatch rawMatBatch) throws SQLException;

    void save(Connection conn, IRawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    void delete(Connection conn, IRawMatBatch rawMatBatch) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IRawMatBatch> searchMatching(Connection conn, IRawMatBatch rawMatBatch) throws SQLException;
}
