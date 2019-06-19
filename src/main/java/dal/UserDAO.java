package dal;

import dal.exceptions.NotFoundException;
import dto.User;
import dto.interfaces.IUser;

import java.sql.*;
import java.util.*;

public class UserDAO implements dal.interfaces.IUserDAO {
    @Override
    public IUser createUser() {
        return new User();
    }

    @Override
    public IUser getObject(Connection conn, int userId) throws NotFoundException, SQLException {

        IUser User = createUser();
        User.setUserId(userId);
        load(conn, User);
        return User;
    }

    @Override
    public void load(Connection conn, IUser User) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM Users WHERE (userId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, User.getUserId());

            singleQuery(conn, stmt, User);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and build a
     * List containing Users.
     * 
     * @param conn This method requires working database connection.
     */
    @Override
    public List<IUser> loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM Users ORDER BY userId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * User contents.
     * 
     * @param conn This method requires working database connection.
     * @param user This parameter contains the class instance to be created. If
     *             automatic surrogate-keys are not used the Primary-key field must
     *             be set for this to work properly.
     */
    @Override
    public synchronized void create(Connection conn, IUser user) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO Users ( userId, userName, ini, cpr, role) VALUES (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, user.getUserId());
            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getIni());
            stmt.setString(4, user.getCpr());
            stmt.setInt(5, user.getRole());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null)
                stmt.close();
        }

    }

    /**
     * save-method. This method will save the current state of User to database.
     *
     * @param conn This method requires working database connection.
     * @param user This parameter contains the class instance to be saved.
     *             Primary-key field must be set for this to work properly.
     */
    @Override
    public void save(Connection conn, IUser user) throws NotFoundException, SQLException {

        String sql = "UPDATE Users SET userName = ?, ini = ?, cpr = ?, " + "role = ? WHERE (userId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getIni());
            stmt.setString(3, user.getCpr());
            stmt.setInt(4, user.getRole());

            stmt.setInt(5, user.getUserId());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were
                // affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * delete-method. This method will remove the information from database as
     * identified by by primary-key in supplied User.
     * 
     * @param conn This method requires working database connection.
     * @param user This parameter contains the class instance to be deleted.
     *             Primary-key field must be set for this to work properly.
     */
    @Override
    public void delete(Connection conn, IUser user) throws NotFoundException, SQLException {

        String sql = "DELETE FROM Users WHERE (userId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getUserId());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                // System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                // System.out.println("PrimaryKey Error when updating DB! (Many objects were
                // deleted!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * deleteAll-method. This method will remove all information from the table that
     * matches this Dao and User couple.
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM Users";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            int rowcount = databaseUpdate(conn, stmt);
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * coutAll-method. This method will return the number of all rows from table
     * that matches this Dao
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public int countAll(Connection conn) throws SQLException {

        String sql = "SELECT count(*) FROM Users";
        PreparedStatement stmt = null;
        ResultSet result = null;
        int allRows = 0;

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next())
                allRows = result.getInt(1);
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
        return allRows;
    }

    /**
     * searchMatching-Method. This method provides searching capability to get
     * matching Users from database.
     *
     * @param conn This method requires working database connection.
     * @param user This parameter contains the class instance where search will be
     *             based. Primary-key field should not be set.
     */
    @Override
    public List searchMatching(Connection conn, IUser user) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM Users WHERE 1=1 ");

        if (user.getUserId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND userId = ").append(user.getUserId()).append(" ");
        }

        if (user.getUserName() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND userName LIKE '").append(user.getUserName()).append("%' ");
        }

        if (user.getIni() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND ini LIKE '").append(user.getIni()).append("%' ");
        }

        if (user.getCpr() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND cpr LIKE '").append(user.getCpr()).append("%' ");
        }

        if (user.getRole() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND role LIKE '").append(user.getRole()).append("%' ");
        }

        sql.append("ORDER BY userId ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList();
        else
            searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

        return searchResults;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use.
     * 
     * @param conn This method requires working database connection.
     * @param stmt This parameter contains the SQL statement to be excuted.
     */
    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use.
     * 
     * @param conn This method requires working database connection.
     * @param stmt This parameter contains the SQL statement to be excuted.
     * @param user Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, IUser user)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                user.setUserId(result.getInt("userId"));
                user.setUserName(result.getString("userName"));
                user.setIni(result.getString("ini"));
                user.setCpr(result.getString("cpr"));
                user.setRole(result.getInt("role"));

            } else {
                // System.out.println("User Object Not Found!");
                throw new NotFoundException("User Object Not Found!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use. It
     * will execute all database queries that will return multiple rows. The
     * resultset will be converted to the List of Users. If no rows were found, an
     * empty List will be returned.
     *
     * @param conn This method requires working database connection.
     * @param stmt This parameter contains the SQL statement to be excuted.
     */
    protected List<IUser> listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                IUser temp = createUser();

                temp.setUserId(result.getInt("userId"));
                temp.setUserName(result.getString("userName"));
                temp.setIni(result.getString("ini"));
                temp.setCpr(result.getString("cpr"));
                temp.setRole(result.getInt("role"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }

        return (List) searchResults;
    }
}