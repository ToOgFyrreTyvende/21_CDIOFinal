package dal;

import java.sql.*;
import java.util.*;
import java.math.*;
import dal.exceptions.NotFoundException;
import dto.ProductBatch;
import dto.interfaces.IProductBatch;

public class ProductBatchDAO implements dal.interfaces.IProductBatchDAO {

    @Override
    public IProductBatch createProductBatch() {
        return new ProductBatch();
    }

    @Override
    public IProductBatch getObject(Connection conn, int prodBatchId) throws NotFoundException, SQLException {

        IProductBatch ProductBatch = createProductBatch();
        ProductBatch.setProdBatchId(prodBatchId);
        load(conn, ProductBatch);
        return ProductBatch;
    }

    @Override
    public void load(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException {

        String sql = "SELECT P.productName, pb.* FROM ProductBatches as pb" +
                "    inner join Products as P on pb.productId = P.productId WHERE (prodBatchId = ? ) ";
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
     * List containing ProductBatch.
     *
     * @param conn This method requires working database connection.
     */
    @Override
    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT P.productName, pb.* FROM ProductBatches as pb inner join Products as P on pb.productId = P.productId" +
                " ORDER BY prodBatchId ASC";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * ProductBatch contents.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch
     */
    @Override
    public synchronized void create(Connection conn, IProductBatch ProductBatch) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO ProductBatches ( prodBatchId, productId, status) VALUES (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, ProductBatch.getProdBatchId());
            stmt.setInt(2, ProductBatch.getProdId());
            stmt.setInt(3, ProductBatch.getStatus());

            int rowcount = databaseUpdate(conn, stmt);
            //ensureRawMatBatches(conn, ProductBatch);
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
     * database.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch
     */
    @Override
    public void save(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException {

        String sql = "UPDATE ProductBatches SET productId = ?, status = ? WHERE (prodBatchId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ProductBatch.getProdId());
            stmt.setInt(2, ProductBatch.getStatus());

            stmt.setInt(3, ProductBatch.getProdBatchId());

            int rowcount = databaseUpdate(conn, stmt);
            //ensureRawMatBatches(conn, ProductBatch);
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

    private void ensureRawMatBatches(Connection conn, IProductBatch product) throws SQLException {
        if(product.getWeighings().size() > 0){
            String sql = "DELETE FROM WeighedIngredientsBatches WHERE prodBatchId = ?";
            PreparedStatement stmt =  conn.prepareStatement(sql);
            stmt.setInt(1, product.getProdBatchId());
            stmt.executeUpdate();
            stmt.close();
        }

        String sql = "INSERT INTO WeighedIngredientsBatches (rawMatBatchId, prodBatchId, userId, tara, netto) " +
                     "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt =  conn.prepareStatement(sql);
        int prodBatchId = product.getProdBatchId();

        for (IProductBatch.IWeighings weighing : product.getWeighings()){
            stmt.setInt(1, weighing.getRawMatId());
            stmt.setInt(2, prodBatchId);
            stmt.setInt(3, weighing.getUserId());
            stmt.setDouble(4, weighing.getTara());
            stmt.setDouble(5, weighing.getNetto());
            stmt.execute();
        }
    }

    /**
     * delete-method. This method will remove the information from database as
     * identified by by primary-key in supplied ProductBatch.
     *
     * @param conn         This method requires working database connection.
     * @param ProductBatch
     */
    @Override
    public void delete(Connection conn, IProductBatch ProductBatch) throws NotFoundException, SQLException {

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
     * matches this Dao and ProductBatch couple.
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
    public List searchMatching(Connection conn, IProductBatch ProductBatch) throws SQLException {

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
            sql.append("AND productId = ").append(ProductBatch.getProdId()).append(" ");
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
     * @param productBatch Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, IProductBatch productBatch)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                productBatch.setName(result.getString("productName"));
                productBatch.setProdBatchId(result.getInt("prodBatchId"));
                productBatch.setProdId(result.getInt("productId"));
                productBatch.setStatus(result.getInt("status"));
                retrieveWeighings(conn, productBatch, result.getInt("prodBatchId"));
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
    private void retrieveWeighings(Connection conn, IProductBatch product, int pbId) throws SQLException {
        String sql = "select rawMatBatchId, rmb.rawMatId, wib.userId, rawMatName, tara, netto, supplier from ProductBatches as pb\n" +
                "inner join WeighedIngredientsBatches as wib on wib.prodBatchId = pb.prodBatchId\n" +
                "inner join RawMatBatches as rmb on wib.rawMatBatchId = rmb.rmbId\n" +
                "inner join RawMats Mat on rmb.rawMatId = Mat.rawMatId\n" +
                "where pb.prodBatchId = ?";

        List<IProductBatch.IWeighings> rmblist = new ArrayList<>();

        PreparedStatement rmb = conn.prepareStatement(sql);
        rmb.setInt(1, pbId);
        ResultSet rmbres = rmb.executeQuery();

        while (rmbres.next()){
            IProductBatch.IWeighings temprmb = new ProductBatch.Weighings();
            temprmb.setRmbId(rmbres.getInt("rawMatBatchId"));
            temprmb.setRawMatId(rmbres.getInt("rawMatId"));
            temprmb.setUserId(rmbres.getInt("userId"));
            temprmb.setName(rmbres.getString("rawMatName"));
            temprmb.setTara(rmbres.getDouble("tara"));
            temprmb.setNetto(rmbres.getDouble("netto"));
            temprmb.setSupplier(rmbres.getString("supplier"));

            rmblist.add(temprmb);
        }

        product.setWeighings(rmblist);
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
                IProductBatch temp = createProductBatch();

                temp.setName(result.getString(("productName")));
                temp.setProdBatchId(result.getInt("prodBatchId"));
                temp.setProdId(result.getInt("productId"));
                temp.setStatus(result.getInt("status"));
                retrieveWeighings(conn, temp, result.getInt("prodBatchId"));

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