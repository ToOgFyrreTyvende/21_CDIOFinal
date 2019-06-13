package dal;

import dal.exceptions.NotFoundException;
import dto.RawMat;
import dto.interfaces.IRawMat;

import java.sql.*;
import java.util.*;
import java.math.*;

public class RawMatDAO implements dal.interfaces.IRawMatDAO, dal.arrayDao.IARawMatDAO {

    private List<IRawMat> rm;

    public RawMatDAO(){rm = new ArrayList<>();}

    @Override
    public IRawMat createRawMat() {
        return new RawMat();
    }

    @Override
    public IRawMat getObject(Connection conn, int rawMatID) throws NotFoundException, SQLException {

        IRawMat rawMat = createRawMat();
        rawMat.setRawMatID(rawMatID);
        load(conn, rawMat);
        return rawMat;
    }

    @Override
    public void load(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM RawMats WHERE (rawMatID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rawMat.getRawMatID());

            singleQuery(conn, stmt, rawMat);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and build a
     * List containing RawMats.
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public List<IRawMat> loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM RawMats ORDER BY rawMatID ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * RawMat contents.
     */
    @Override
    public synchronized void create(Connection conn, IRawMat rawMat) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO RawMats ( rawMatID, rawMatName) VALUES (?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, rawMat.getRawMatID());
            stmt.setString(2, rawMat.getRawMatName());

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
     * save-method. This method will save the current state of RawMat to database.
     *
     * @param conn   This method requires working database connection.
     * @param rawMat This parameter contains the class instance to be saved.
     *               Primary-key field must be set for this to work properly.
     */
    @Override
    public void save(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException {

        String sql = "UPDATE RawMats SET rawMatName = ? WHERE (rawMatID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, rawMat.getRawMatName());

            stmt.setInt(2, rawMat.getRawMatID());

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
     * identified by by primary-key in supplied rawMat.
     *
     * @param conn   This method requires working database connection.
     * @param rawMat This parameter contains the class instance to be deleted.
     *               Primary-key field must be set for this to work properly.
     */
    @Override
    public void delete(Connection conn, IRawMat rawMat) throws NotFoundException, SQLException {

        String sql = "DELETE FROM RawMats WHERE (rawMatID = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, rawMat.getRawMatID());

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
     * matches this Dao and RawMat couple.
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM RawMats";
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
    @Override
    public int countAll(Connection conn) throws SQLException {

        String sql = "SELECT count(*) FROM RawMats";
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
     * matching RawMats from database.
     *
     * @param conn   This method requires working database connection.
     * @param rawMat This parameter contains the class instance where search will be
     *               based. Primary-key field should not be set.
     */
    @Override
    public List<IRawMat> searchMatching(Connection conn, IRawMat rawMat) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM RawMats WHERE 1=1 ");

        if (rawMat.getRawMatID() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatID = ").append(rawMat.getRawMatID()).append(" ");
        }

        if (rawMat.getRawMatName() != null) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatName LIKE '").append(rawMat.getRawMatName()).append("%' ");
        }

        sql.append("ORDER BY rawMatID ASC ");

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
     * @param conn   This method requires working database connection.
     * @param stmt   This parameter contains the SQL statement to be excuted.
     * @param rawMat Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, IRawMat rawMat)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                rawMat.setRawMatID(result.getInt("rawMatID"));
                rawMat.setRawMatName(result.getString("rawMatName"));

            } else {
                // System.out.println("RawMat Object Not Found!");
                throw new NotFoundException("RawMat Object Not Found!");
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
                IRawMat temp = createRawMat();

                temp.setRawMatID(result.getInt("rawMatID"));
                temp.setRawMatName(result.getString("rawMatName"));

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

    //============================      array       ==============================





    @Override
    public IRawMat getRawMat(int rawmatId) {
        return rm.get(rawmatId);
    }

    @Override
    public List<IRawMat> getRawMatList() {
        return rm;
    }

    @Override
    public void createRawMat(IRawMat rawmat) {
        rm.add(rawmat);
    }

    @Override
    public void updateRawMat(IRawMat rawmat) {
        int rmID = rawmat.getRawMatID();

        int i = 0;
        for (IRawMat upRawMat : rm){
            if (upRawMat.getRawMatID() == rmID){
                rm.set(i, upRawMat);
            }
            i++;
        }
    }

    @Override
    public void deleteRawMat(int rawmatId) {
        rm.remove(rawmatId);
    }
}