package dal.interfaces;

import dal.exceptions.NotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {

    T getObject(Connection conn, int DTOId) throws NotFoundException, SQLException;

    void load(Connection conn, T DTO) throws NotFoundException, SQLException;

    List<T> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, T DTO) throws SQLException;

    void save(Connection conn, T DTO) throws NotFoundException, SQLException;

    void delete(Connection conn, T DTO) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<T> searchMatching(Connection conn, T DTO) throws SQLException;
}
