package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User createUser();

    User getObject(Connection conn, int userId) throws NotFoundException, SQLException;

    void load(Connection conn, User User) throws NotFoundException, SQLException;

    List loadAll(Connection conn) throws SQLException;

    void create(Connection conn, User user) throws SQLException;

    void save(Connection conn, User user) throws NotFoundException, SQLException;

    void delete(Connection conn, User user) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List searchMatching(Connection conn, User user) throws SQLException;
}
