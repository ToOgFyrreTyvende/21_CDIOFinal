package dal;

import dal.exceptions.NotFoundException;
import dal.interfaces.IWeighedIngredientsBatchesDAO;
import dto.WeighedIngredientsBatches;
import dto.interfaces.IWeighedIngredientsBatches;

import java.sql.*;
import java.util.*;

public class WeighedIngredientsBatchesDAO implements IWeighedIngredientsBatchesDAO {

    @Override
    public IWeighedIngredientsBatches createweighedBatch() {
        return new WeighedIngredientsBatches();
    }

    @Override
    public IWeighedIngredientsBatches getObject(Connection conn, int weighedIngredientId) throws NotFoundException, SQLException {

        IWeighedIngredientsBatches weighedBatch = createweighedBatch();
        weighedBatch.setWeighedIngredientId(weighedIngredientId);
        load(conn, weighedBatch);
        return weighedBatch;
    }

    @Override
    public void load(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM WeighedBatches WHERE (weighedIngredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getWeighedIngredientId());

            singleQuery(conn, stmt, weighedBatch);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    @Override
    public List<IWeighedIngredientsBatches> loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM WeighedBatches ORDER BY weighedIngredientId ASC ";
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
    @Override
    public synchronized void create(Connection conn, IWeighedIngredientsBatches weighedBatch) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO WeighedBatches ( weighedIngredientId, prodBatchId, rawMatBatchId, userId, "
                    + "tara, netto) VALUES (?, ?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, weighedBatch.getWeighedIngredientId());
            stmt.setInt(2, weighedBatch.getProdBatchId());
            stmt.setInt(3, weighedBatch.getRawMatBatchId());
            stmt.setInt(4, weighedBatch.getUserId());
            stmt.setDouble(5, weighedBatch.getTara());
            stmt.setDouble(6, weighedBatch.getNetto());

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
    @Override
    public void save(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException {

        String sql = "UPDATE WeighedBatches SET rawMatBatchId = ?, userId = ?, tara = ?, "
                + "netto = ?, prodBatchId = ? WHERE (weighedIngredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getRawMatBatchId());
            stmt.setInt(2, weighedBatch.getUserId());
            stmt.setDouble(3, weighedBatch.getTara());
            stmt.setDouble(4, weighedBatch.getNetto());

            stmt.setInt(5, weighedBatch.getProdBatchId());
            stmt.setInt(6, weighedBatch.getWeighedIngredientId());

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
    @Override
    public void delete(Connection conn, IWeighedIngredientsBatches weighedBatch) throws NotFoundException, SQLException {

        String sql = "DELETE FROM WeighedBatches WHERE (weighedIngredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, weighedBatch.getWeighedIngredientId());

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
    @Override
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
    @Override
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
    @Override
    public List searchMatching(Connection conn, IWeighedIngredientsBatches weighedBatch) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM WeighedBatches WHERE 1=1 ");

        if (weighedBatch.getWeighedIngredientId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND weighedIngredientId = ").append(weighedBatch.getWeighedIngredientId()).append(" ");
        }

        if (weighedBatch.getRawMatBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatBatchId = ").append(weighedBatch.getRawMatBatchId()).append(" ");
        }

        if (weighedBatch.getProdBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND prodBatchId = ").append(weighedBatch.getProdBatchId()).append(" ");
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

        sql.append("ORDER BY weighedIngredientId ASC ");

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
    protected void singleQuery(Connection conn, PreparedStatement stmt, IWeighedIngredientsBatches weighedBatch)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                weighedBatch.setWeighedIngredientId(result.getInt("weighedIngredientId"));
                weighedBatch.setRawMatBatchId(result.getInt("rawMatBatchId"));
                weighedBatch.setUserId(result.getInt("userId"));
                weighedBatch.setTara(result.getDouble("tara"));
                weighedBatch.setNetto(result.getDouble("netto"));

            } else {
                // System.out.println("WeighedIngredientsBatches Object Not Found!");
                throw new NotFoundException("WeighedIngredientsBatches Object Not Found!");
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
    protected List<IWeighedIngredientsBatches> listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                IWeighedIngredientsBatches temp = createweighedBatch();

                temp.setWeighedIngredientId(result.getInt("weighedIngredientId"));
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
