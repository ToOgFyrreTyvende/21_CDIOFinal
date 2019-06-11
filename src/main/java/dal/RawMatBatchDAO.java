package dal;

import dal.exceptions.NotFoundException;
import dto.RawMatBatch;

import java.sql.*;
import java.util.*;
import java.math.*;

public class RawMatBatchDAO {
    public RawMatBatch createrawMatBatch() {
        return new RawMatBatch();
    }

    public RawMatBatch getObject(Connection conn, int rmbId) throws NotFoundException, SQLException {

        RawMatBatch rawMatBatch = createrawMatBatch();
        rawMatBatch.setRmbId(rmbId);
        load(conn, rawMatBatch);
        return rawMatBatch;
    }

    public void load(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM RawMatBatches WHERE (rmbId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rawMatBatch.getRmbId());

            singleQuery(conn, stmt, rawMatBatch);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and build a
     * List containing rawMatBatchs.
     *
     * @param conn This method requires working database connection.
     */
    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM RawMatBatches ORDER BY rmbId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * rawMatBatch contents.
     * 
     * @param conn        This method requires working database connection.
     * @param rawMatBatch
     */
    public synchronized void create(Connection conn, RawMatBatch rawMatBatch) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO RawMatBatches ( rmbId, rawMatId, amount, " + "supplier) VALUES (?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, rawMatBatch.getRmbId());
            stmt.setInt(2, rawMatBatch.getRawMatId());
            stmt.setDouble(3, rawMatBatch.getAmount());
            stmt.setString(4, rawMatBatch.getSupplier());

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
     * save-method. This method will save the current state of rawMatBatch to
     * database.
     *
     * @param conn        This method requires working database connection.
     * @param rawMatBatch
     */
    public void save(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException {

        String sql = "UPDATE RawMatBatches SET rawMatId = ?, amount = ?, supplier = ? WHERE (rmbId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rawMatBatch.getRawMatId());
            stmt.setDouble(2, rawMatBatch.getAmount());
            stmt.setString(3, rawMatBatch.getSupplier());

            stmt.setInt(4, rawMatBatch.getRmbId());

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
     * identified by by primary-key in supplied rawMatBatch.
     *
     * @param conn        This method requires working database connection.
     * @param rawMatBatch
     */
    public void delete(Connection conn, RawMatBatch rawMatBatch) throws NotFoundException, SQLException {

        String sql = "DELETE FROM RawMatBatches WHERE (rmbId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rawMatBatch.getRmbId());

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
     * matches this Dao and rawMatBatch couple.
     *
     * @param conn This method requires working database connection.
     */
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM RawMatBatches";
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
     * that matches this Dao.
     *
     * @param conn This method requires working database connection.
     */
    public int countAll(Connection conn) throws SQLException {

        String sql = "SELECT count(*) FROM RawMatBatches";
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
     * matching rawMatBatchs from database.
     * 
     * @param conn        This method requires working database connection.
     * @param rawMatBatch This parameter contains the class instance where search
     *                    will be based. Primary-key field should not be set.
     */
    public List searchMatching(Connection conn, RawMatBatch rawMatBatch) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM RawMatBatches WHERE 1=1 ");

        if (rawMatBatch.getRmbId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rmbId = ").append(rawMatBatch.getRmbId()).append(" ");
        }

        if (rawMatBatch.getRawMatId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatId LIKE '").append(rawMatBatch.getRawMatId()).append("%' ");
        }

        if (rawMatBatch.getAmount() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND amount = ").append(rawMatBatch.getAmount()).append(" ");
        }

        if (rawMatBatch.getSupplier() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND supplier LIKE '").append(rawMatBatch.getSupplier()).append("%' ");
        }

        sql.append("ORDER BY rmbId ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList();
        else
            searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

        return searchResults;
    }

    /**
     * getDaogenVersion will return information about generator which created these
     * sources.
     */
    public String getDaogenVersion() {
        return "DaoGen version 2.4.1";
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
     * @param conn        This method requires working database connection.
     * @param stmt        This parameter contains the SQL statement to be excuted.
     * @param rawMatBatch Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, RawMatBatch rawMatBatch)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                rawMatBatch.setRmbId(result.getInt("rmbId"));
                rawMatBatch.setRawMatId(result.getInt("rawMatId"));
                rawMatBatch.setAmount(result.getDouble("amount"));
                rawMatBatch.setSupplier(result.getString("supplier"));

            } else {
                // System.out.println("RawMatBatch Object Not Found!");
                throw new NotFoundException("RawMatBatch Object Not Found!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * databaseQuery-method. This method is a helper method for internal use.
     *
     * @param conn This method requires working database connection.
     * @param stmt This parameter contains the SQL statement to be excuted.
     */
    protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                RawMatBatch temp = createrawMatBatch();

                temp.setRmbId(result.getInt("rmbId"));
                temp.setRawMatId(result.getInt("rawMatId"));
                temp.setAmount(result.getDouble("amount"));
                temp.setSupplier(result.getString("supplier"));

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