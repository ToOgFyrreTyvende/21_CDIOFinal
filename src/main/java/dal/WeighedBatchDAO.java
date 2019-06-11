package dal;

import dal.exceptions.NotFoundException;
import dto.WeighedBatch;

import java.sql.*;
import java.util.*;
import java.math.*;

public class WeighedBatchDAO {

    public WeighedBatch createweighedBatch() {
        return new WeighedBatch();
    }

    public WeighedBatch getObject(Connection conn, int weighedBatchId) throws NotFoundException, SQLException {

        WeighedBatch weighedBatch = createweighedBatch();
        weighedBatch.setWeighedBatchId(weighedBatchId);
        load(conn, weighedBatch);
        return weighedBatch;
    }

    public void load(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM WeighedBatches WHERE (weighedBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getWeighedBatchId());

            singleQuery(conn, stmt, weighedBatch);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM WeighedBatches ORDER BY weighedBatchId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * weighedBatch contents.
     *
     * @param conn         This method requires working database connection.
     * @param weighedBatch
     */
    public synchronized void create(Connection conn, WeighedBatch weighedBatch) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO WeighedBatches ( weighedBatchId, rawMatBatchId, userId, "
                    + "tara, netto) VALUES (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, weighedBatch.getWeighedBatchId());
            stmt.setInt(2, weighedBatch.getRawMatBatchId());
            stmt.setInt(3, weighedBatch.getUserId());
            stmt.setDouble(4, weighedBatch.getTara());
            stmt.setDouble(5, weighedBatch.getNetto());

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
     * save-method. This method will save the current state of weighedBatch to
     * database.
     * 
     * @param conn         This method requires working database connection.
     * @param weighedBatch This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    public void save(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException {

        String sql = "UPDATE WeighedBatches SET rawMatBatchId = ?, userId = ?, tara = ?, "
                + "netto = ? WHERE (weighedBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getRawMatBatchId());
            stmt.setInt(2, weighedBatch.getUserId());
            stmt.setDouble(3, weighedBatch.getTara());
            stmt.setDouble(4, weighedBatch.getNetto());

            stmt.setInt(5, weighedBatch.getWeighedBatchId());

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
     * identified by by primary-key in supplied weighedBatch.
     *
     * @param conn         This method requires working database connection.
     * @param weighedBatch This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    public void delete(Connection conn, WeighedBatch weighedBatch) throws NotFoundException, SQLException {

        String sql = "DELETE FROM WeighedBatches WHERE (weighedBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getWeighedBatchId());

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
     * matches this Dao and weighedBatch couple.
     * 
     * @param conn This method requires working database connection.
     */
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM WeighedBatches";
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

        String sql = "SELECT count(*) FROM WeighedBatches";
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
     * matching weighedBatchs from database.
     *
     * @param conn         This method requires working database connection.
     * @param weighedBatch This parameter contains the class instance where search
     *                     will be based. Primary-key field should not be set.
     */
    public List searchMatching(Connection conn, WeighedBatch weighedBatch) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM WeighedBatches WHERE 1=1 ");

        if (weighedBatch.getWeighedBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND weighedBatchId = ").append(weighedBatch.getWeighedBatchId()).append(" ");
        }

        if (weighedBatch.getRawMatBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatBatchId = ").append(weighedBatch.getRawMatBatchId()).append(" ");
        }

        if (weighedBatch.getUserId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND userId = ").append(weighedBatch.getUserId()).append(" ");
        }

        if (weighedBatch.getTara() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND tara = ").append(weighedBatch.getTara()).append(" ");
        }

        if (weighedBatch.getNetto() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND netto = ").append(weighedBatch.getNetto()).append(" ");
        }

        sql.append("ORDER BY weighedBatchId ASC ");

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
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @param weighedBatch Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, WeighedBatch weighedBatch)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                weighedBatch.setWeighedBatchId(result.getInt("weighedBatchId"));
                weighedBatch.setRawMatBatchId(result.getInt("rawMatBatchId"));
                weighedBatch.setUserId(result.getInt("userId"));
                weighedBatch.setTara(result.getDouble("tara"));
                weighedBatch.setNetto(result.getDouble("netto"));

            } else {
                // System.out.println("WeighedBatch Object Not Found!");
                throw new NotFoundException("WeighedBatch Object Not Found!");
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
                WeighedBatch temp = createweighedBatch();

                temp.setWeighedBatchId(result.getInt("weighedBatchId"));
                temp.setRawMatBatchId(result.getInt("rawMatBatchId"));
                temp.setUserId(result.getInt("userId"));
                temp.setTara(result.getDouble("tara"));
                temp.setNetto(result.getDouble("netto"));

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