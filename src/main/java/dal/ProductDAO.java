package dal;

import dal.exceptions.NotFoundException;
import dto.Product;
import dto.interfaces.IProduct;
import dto.interfaces.IProductBatch;

import java.sql.*;
import java.util.*;


public class ProductDAO implements dal.interfaces.IProductDAO {

    @Override
    public Product createproduct() {
        return new Product();
    }

    @Override
    public Product getObject(Connection conn, int productId) throws NotFoundException, SQLException {

        Product product = createproduct();
        product.setProductId(productId);
        load(conn, product);
        return product;
    }

    @Override
    public void load(Connection conn, Product product) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM Products WHERE (productId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getProductId());

            singleQuery(conn, stmt, product);
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    /**
     * LoadAll-method. This will read all contents from database table and
     * build a List containing products.
     *
     * @param conn         This method requires working database connection.
     */
    @Override
    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM Products ORDER BY productId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * products contents.
     *
     * @param conn         This method requires working database connection.
     * @param product  This parameter contains the class instance to be created.
     *                     If automatic surrogate-keys are not used the Primary-key
     *                     field must be set for this to work properly.
     */
    @Override
    public synchronized void create(Connection conn, Product product) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO Products ( productId, productName, "
                    + "nomNetto, tolerance) VALUES (?, ?, ?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setDouble(4, product.getNomNetto());
            stmt.setDouble(5, product.getTolerance());

            int rowcount = databaseUpdate(conn, stmt);
            ensureIngredients(conn, product);
            if (rowcount != 1) {
                //System.out.println("PrimaryKey Error when updating DB!");
                throw new SQLException("PrimaryKey Error when updating DB!");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * save-method. This method will save the current state of products to database.
     *
     * @param conn         This method requires working database connection.
     * @param product  This parameter contains the class instance to be saved.
     *                     Primary-key field must be set for this to work properly.
     */
    @Override
    public void save(Connection conn, Product product)
            throws NotFoundException, SQLException {

        String sql = "UPDATE Products SET productName = ?, nomNetto = ?, "
                + "tolerance = ? WHERE (productId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, product.getProductName());
            stmt.setDouble(3, product.getNomNetto());
            stmt.setDouble(4, product.getTolerance());

            stmt.setInt(5, product.getProductId());

            int rowcount = databaseUpdate(conn, stmt);
            ensureIngredients(conn, product);

            if (rowcount == 0) {
                //System.out.println("Object could not be saved! (PrimaryKey not found)");
                throw new NotFoundException("Object could not be saved! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were affected!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were affected!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    private void ensureIngredients(Connection conn, IProduct product) throws SQLException {
        if(product.getIngredients().size() > 0){
            String sql = "DELETE FROM ProductIngredients WHERE productId = ?";
            PreparedStatement stmt =  conn.prepareStatement(sql);
            stmt.setInt(1, product.getProductId());
            stmt.executeUpdate();
            stmt.close();
        }

        String sql = "INSERT INTO ProductIngredients (rawMatId, productId, amount) VALUES (?, ?, ?)";
        PreparedStatement stmt =  conn.prepareStatement(sql);
        int prodid = product.getProductId();

        for (IProduct.IRawMatAmount rawmat : product.getIngredients()){
            stmt.setInt(1, rawmat.getRawMatId());
            stmt.setInt(2, prodid);
            stmt.setDouble(3, rawmat.getAmount());
            stmt.execute();
        }
    }


    /**
     * delete-method. This method will remove the information from database as identified by
     * by primary-key in supplied product.
     *
     * @param conn         This method requires working database connection.
     * @param product  This parameter contains the class instance to be deleted.
     *                     Primary-key field must be set for this to work properly.
     */
    @Override
    public void delete(Connection conn, Product product)
            throws NotFoundException, SQLException {

        String sql = "DELETE FROM Products WHERE (productId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, product.getProductId());

            int rowcount = databaseUpdate(conn, stmt);
            if (rowcount == 0) {
                //System.out.println("Object could not be deleted (PrimaryKey not found)");
                throw new NotFoundException("Object could not be deleted! (PrimaryKey not found)");
            }
            if (rowcount > 1) {
                //System.out.println("PrimaryKey Error when updating DB! (Many objects were deleted!)");
                throw new SQLException("PrimaryKey Error when updating DB! (Many objects were deleted!)");
            }
        } finally {
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * deleteAll-method. This method will remove all information from the table that matches
     * this Dao and products couple.
     *
     * @param conn         This method requires working database connection.
     */
    @Override
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM Products";
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
     * coutAll-method. This method will return the number of all rows from table that matches
     * this Dao.
     *
     * @param conn         This method requires working database connection.
     */
    @Override
    public int countAll(Connection conn) throws SQLException {

        String sql = "SELECT count(*) FROM Products";
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
     * searchMatching-Method. This method provides searching capability to
     * get matching products from database. It works by searching all
     * objects that match permanent instance variables of given object.
     *
     * @param conn         This method requires working database connection.
     * @param product  This parameter contains the class instance where search will be based.
     *                     Primary-key field should not be set.
     */
    @Override
    public List searchMatching(Connection conn, Product product) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE 1=1 ");

        if (product.getProductId() != 0) {
            if (first) { first = false; }
            sql.append("AND productId = ").append(product.getProductId()).append(" ");
        }

        if (product.getProductName() != null) {
            if (first) { first = false; }
            sql.append("AND productName LIKE '").append(product.getProductName()).append("%' ");
        }

        if (product.getNomNetto() != 0) {
            if (first) { first = false; }
            sql.append("AND nomNetto = ").append(product.getNomNetto()).append(" ");
        }

        if (product.getTolerance() != 0) {
            if (first) { first = false; }
            sql.append("AND tolerance = ").append(product.getTolerance()).append(" ");
        }


        sql.append("ORDER BY productId ASC ");

        // Prevent accidential full table results.
        // Use loadAll if all rows must be returned.
        if (first)
            searchResults = new ArrayList();
        else
            searchResults = listQuery(conn, conn.prepareStatement(sql.toString()));

        return searchResults;
    }


    /**
     * databaseUpdate-method. This method is a helper method for internal use. It will execute
     * all database handling that will change the information in tables. SELECT queries will
     * not be executed here however. The return value indicates how many rows were affected.
     * This method will also make sure that if cache is used, it will reset when data changes.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected int databaseUpdate(Connection conn, PreparedStatement stmt) throws SQLException {

        int result = stmt.executeUpdate();

        return result;
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return only one row. The resultset will be converted
     * to product. If no rows were found, NotFoundException will be thrown.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     * @param product  Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, Product product)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                product.setProductId(result.getInt("productId"));
                product.setProductName(result.getString("productName"));
                product.setNomNetto(result.getDouble("nomNetto"));
                product.setTolerance(result.getDouble("tolerance"));

                String sql =
                        "select RM.rawMatId, RM.rawMatName, amount\n" +
                        "from ProductIngredients\n" +
                        "  inner join RawMats RM on ProductIngredients.rawMatId = RM.rawMatID\n" +
                        "  inner join Products pr on ProductIngredients.productId = pr.productId\n" +
                        "where ProductIngredients.productId = ?;";

                List<IProduct.IRawMatAmount> inglist = new ArrayList<>();

                PreparedStatement ingredients = null;
                ingredients = conn.prepareStatement(sql);
                ingredients.setInt(1, result.getInt("productId"));
                ResultSet ingres = ingredients.executeQuery();

                while (ingres.next()){
                    Product.RawMatAmount temping = new Product.RawMatAmount();
                    temping.setAmount(ingres.getDouble("amount"));
                    temping.setName(ingres.getString("rawMatName"));
                    temping.setRawMatId(ingres.getInt("rawMatId"));

                    inglist.add(temping);
                }

                product.setIngredients(inglist);
                } else {
                //System.out.println("Product Object Not Found!");
                throw new NotFoundException("Product Object Not Found!");
            }
        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }
    }


    /**
     * databaseQuery-method. This method is a helper method for internal use. It will execute
     * all database queries that will return multiple rows. The resultset will be converted
     * to the List of products. If no rows were found, an empty List will be returned.
     *
     * @param conn         This method requires working database connection.
     * @param stmt         This parameter contains the SQL statement to be excuted.
     */
    protected List listQuery(Connection conn, PreparedStatement stmt) throws SQLException {

        ArrayList searchResults = new ArrayList();
        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            while (result.next()) {
                Product temp = createproduct();

                temp.setProductId(result.getInt("productId"));
                temp.setProductName(result.getString("productName"));
                temp.setNomNetto(result.getDouble("nomNetto"));
                temp.setTolerance(result.getDouble("tolerance"));

                searchResults.add(temp);
            }

        } finally {
            if (result != null)
                result.close();
            if (stmt != null)
                stmt.close();
        }

        return (List)searchResults;
    }

}