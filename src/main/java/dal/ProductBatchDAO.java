package dal;

import java.sql.*;
import java.util.*;
import java.math.*;
import dal.exceptions.NotFoundException;
import dto.ProductBatch;

public class ProductBatchDAO implements dal.interfaces.IProductBatchDAO {

    @Override
    public ProductBatch createProductBatch() {
        return new ProductBatch();
    }

    @Override
    public ProductBatch getObject(Connection conn, int prodBatchId) throws NotFoundException, SQLException {

        ProductBatch ProductBatch = createProductBatch();
        ProductBatch.setProdBatchId(prodBatchId);
        load(conn, ProductBatch);
        return ProductBatch;
    }

    @Override
    public void load(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM ProductBatches WHERE (prodBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ProductBatch.getProdBatchId());

            singleQuery(conn, stmt, ProductBatch);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and build a
     * List containing ProductBatch. Please note, that this method will consume huge
     * amounts of resources if table has lot's of rows. This should only be used
     * when target tables have only small amounts of data.
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM ProductBatches ORDER BY prodBatchId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * ProductBatch contents. Make sure that values for all NOT NULL columns are
     * correctly specified. Also, if this table does not use automatic
     * surrogate-keys the primary-key must be specified. After INSERT command this
     * method will read the generated primary-key back to ProductBatch if automatic
     * surrogate-keys were used.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key
     *                     field must be set for this to work properly.
     */
    @Override
    public synchronized void create(Connection conn, ProductBatch ProductBatch) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO ProductBatches ( prodId, rawMatBatchId, status) VALUES (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ProductBatch.getProdId());
            stmt.setInt(2, ProductBatch.getRawMatBatchId());
            stmt.setInt(3, ProductBatch.getStatus());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount != 1) {
                // System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }

        } finally {
            if (stmt != null)
                stmt.close();
        }

        /**
         * The following query will read the automatically generated primary key back to
         * ProductBatch. This must be done to make things consistent for upper layer
         * processing logic.
         */
        sql = "SELECT last_insert_id()";

        try {
            stmt = conn.prepareStatement(sql);
            result = stmt.executeQuery();

            if (result.next()) {

                ProductBatch.setProdBatchId((int) result.getLong(1));

            } else {
                // System.out.println("Unable to find primary-key for created object!");
                throw new SQLException("Unable to find primary-key for created object!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }

    }

    /**
     * save-method. This method will save the current state of ProductBatch to
     * database. Save can not be used to create new instances in database, so upper
     * layer must make sure that the primary-key is correctly specified. Primary-key
     * will indicate which instance is going to be updated in database. If save can
     * not find matching row, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    @Override
    public void save(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException {

        String sql = "UPDATE ProductBatches SET prodId = ?, rawMatBatchId = ?, status = ? WHERE (prodBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ProductBatch.getProdId());
            stmt.setInt(2, ProductBatch.getRawMatBatchId());
            stmt.setInt(3, ProductBatch.getStatus());

            stmt.setInt(4, ProductBatch.getProdBatchId());

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
     * identified by by primary-key in supplied ProductBatch. Once ProductBatch has
     * been deleted it can not be restored by calling save. Restoring can only be
     * done using create method but if database is using automatic surrogate-keys,
     * the resulting object will have different primary-key than what it was in the
     * deleted object. If delete can not find matching row, NotFoundException will
     * be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    @Override
    public void delete(Connection conn, ProductBatch ProductBatch) throws NotFoundException, SQLException {

        String sql = "DELETE FROM ProductBatches WHERE (prodBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ProductBatch.getProdBatchId());

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
     * matches this Dao and ProductBatch couple. This should be the most efficient
     * way to clear table. Once deleteAll has been called, no ProductBatch that has
     * been created before can be restored by calling save. Restoring can only be
     * done using create method but if database is using automatic surrogate-keys,
     * the resulting object will have different primary-key than what it was in the
     * deleted object. (Note, the implementation of this method should be different
     * with different DB backends.)
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM ProductBatches";
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

        String sql = "SELECT count(*) FROM ProductBatches";
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
     * matching ProductBatch from database.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch This parameter contains the class instance where search
     *                     will be based. Primary-key field should not be set.
     */
    @Override
    public List searchMatching(Connection conn, ProductBatch ProductBatch) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM ProductBatches WHERE 1=1 ");

        if (ProductBatch.getProdBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND prodBatchId = ").append(ProductBatch.getProdBatchId()).append(" ");
        }

        if (ProductBatch.getProdId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND prodId = ").append(ProductBatch.getProdId()).append(" ");
        }

        if (ProductBatch.getRawMatBatchId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatBatchId = ").append(ProductBatch.getRawMatBatchId()).append(" ");
        }

        if (ProductBatch.getStatus() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND status = ").append(ProductBatch.getStatus()).append(" ");
        }

        sql.append("ORDER BY prodBatchId ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList();
        else
            searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

        return searchResults;
    }

    /**
     * databaseUpdate-method. This method is a helper method for internal use. It
     * will execute all database handling that will change the information in
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
     * @param ProductBatch Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, ProductBatch ProductBatch)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                ProductBatch.setProdBatchId(result.getInt("prodBatchId"));
                ProductBatch.setProdId(result.getInt("prodId"));
                ProductBatch.setRawMatBatchId(result.getInt("rawMatBatchId"));
                ProductBatch.setStatus(result.getInt("status"));

            } else {
                // System.out.println("ProductBatch Object Not Found!");
                throw new NotFoundException("ProductBatch Object Not Found!");
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
     * will execute all database queries that will return multiple rows.
     *
     * @param conn This method requires working database connection.
     * @param stmt This parameter contains the SQL statement to be executed.
     */
    protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                ProductBatch temp = createProductBatch();

                temp.setProdBatchId(result.getInt("prodBatchId"));
                temp.setProdId(result.getInt("prodId"));
                temp.setRawMatBatchId(result.getInt("rawMatBatchId"));
                temp.setStatus(result.getInt("status"));

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