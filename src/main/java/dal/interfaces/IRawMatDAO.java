package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.RawMat;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IRawMatDAO {
    RawMat createRawMat();

    RawMat getObject(Connection conn, int rawMatID) throws NotFoundException, SQLException;

    void load(Connection conn, RawMat rawMat) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, RawMat rawMat) throws SQLException;

    void save(Connection conn, RawMat rawMat) throws NotFoundException, SQLException;

    void delete(Connection conn, RawMat rawMat) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, RawMat rawMat) throws SQLException;
}
