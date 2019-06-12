package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.IRawMat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRawMatDAO {
    IRawMat createRawMat();

    IRawMat getObject(Connection conn, int rawMatID) throws NotFoundException, SQLException;

    void load(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException;

    List<IRawMat> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IRawMat rawMat) throws SQLException;

    void save(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException;

    void delete(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IRawMat> searchMatching(Connection conn, IRawMat rawMat) throws SQLException;
}
