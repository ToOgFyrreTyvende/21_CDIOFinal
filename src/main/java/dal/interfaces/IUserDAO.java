package dal.interfaces;

import dal.exceptions.NotFoundException;
import dto.interfaces.IUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    IUser createUser();

    IUser getObject(Connection conn, int userId) throws NotFoundException, SQLException;

    void load(Connection conn, IUser User) throws NotFoundException, SQLException;

    List<IUser> loadAll(Connection conn) throws SQLException;

    void create(Connection conn, IUser user) throws SQLException;

    void save(Connection conn, IUser user) throws NotFoundException, SQLException;

    void delete(Connection conn, IUser user) throws NotFoundException, SQLException;

    void deleteAll(Connection conn) throws SQLException;

    int countAll(Connection conn) throws SQLException;

    List<IUser> searchMatching(Connection conn, IUser user) throws SQLException;
}
