package dal;

import dal.exceptions.NotFoundException;
import dto.ProductIngredient;

import java.sql.*;
import java.util.*;
import java.math.*;

public class ProductIngredientDAO {

    public ProductIngredient createproductIngredient() {
        return new ProductIngredient();
    }

    public ProductIngredient getObject(Connection conn, int ingredientId) throws NotFoundException, SQLException {

        ProductIngredient productIngredient = createproductIngredient();
        productIngredient.setIngredientId(ingredientId);
        load(conn, productIngredient);
        return productIngredient;
    }

    public void load(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException {

        String sql = "SELECT * FROM ProductIngredients WHERE (ingredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIngredient.getIngredientId());

            singleQuery(conn, stmt, productIngredient);

        } finally {
            if (stmt != null)
                stmt.close();
        }
    }

    public List loadAll(Connection conn) throws SQLException {

        String sql = "SELECT * FROM ProductIngredients ORDER BY ingredientId ASC ";
        List searchResults = listQuery(conn, conn.prepareStatement(sql));

        return searchResults;
    }

    /**
     * create-method. This will create new row in database according to supplied
     * productIngredient contents.
     *
     * @param conn              This method requires working database connection.
     * @param productIngredient This parameter contains the class instance to be
     *                          created. If automatic surrogate-keys are not used
     *                          the Primary-key field must be set for this to work
     *                          properly.
     */
    public synchronized void create(Connection conn, ProductIngredient productIngredient) throws SQLException {

        String sql = "";
        PreparedStatement stmt = null;
        ResultSet result = null;

        try {
            sql = "INSERT INTO ProductIngredients ( ingredientId, rawMatId, productId) VALUES (?, ?, ?) ";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, productIngredient.getIngredientId());
            stmt.setInt(2, productIngredient.getRawMatId());
            stmt.setInt(3, productIngredient.getProductId());

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
     * save-method. This method will save the current state of productIngredient to
     * database.
     * 
     * @param conn              This method requires working database connection.
     * @param productIngredient
     */
    public void save(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException {

        String sql = "UPDATE ProductIngredients SET rawMatId = ?, productId = ? WHERE (ingredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIngredient.getRawMatId());
            stmt.setInt(2, productIngredient.getProductId());

            stmt.setInt(3, productIngredient.getIngredientId());

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
     * identified by by primary-key in supplied productIngredient.
     *
     * @param conn              This method requires working database connection.
     * @param productIngredient
     */
    public void delete(Connection conn, ProductIngredient productIngredient) throws NotFoundException, SQLException {

        String sql = "DELETE FROM ProductIngredients WHERE (ingredientId = ? ) ";
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, productIngredient.getIngredientId());

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
     * matches this Dao and productIngredient couple.
     *
     * @param conn This method requires working database connection.
     */
    public void deleteAll(Connection conn) throws SQLException {

        String sql = "DELETE FROM ProductIngredients";
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

        String sql = "SELECT count(*) FROM ProductIngredients";
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
     * matching productIngredients from database.
     * 
     * @param conn              This method requires working database connection.
     * @param productIngredient
     */
    public List searchMatching(Connection conn, ProductIngredient productIngredient) throws SQLException {

        List searchResults;

        boolean first = true;
        StringBuffer sql = new StringBuffer("SELECT * FROM ProductIngredients WHERE 1=1 ");

        if (productIngredient.getIngredientId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND ingredientId = ").append(productIngredient.getIngredientId()).append(" ");
        }

        if (productIngredient.getRawMatId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND rawMatId = ").append(productIngredient.getRawMatId()).append(" ");
        }

        if (productIngredient.getProductId() != 0) {
            if (first) {
                first = false;
            }
            sql.append("AND productId = ").append(productIngredient.getProductId()).append(" ");
        }

        sql.append("ORDER BY ingredientId ASC ");

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
     * @param conn              This method requires working database connection.
     * @param stmt              This parameter contains the SQL statement to be
     *                          excuted.
     * @param productIngredient Class-instance where resulting data will be stored.
     */
    protected void singleQuery(Connection conn, PreparedStatement stmt, ProductIngredient productIngredient)
            throws NotFoundException, SQLException {

        ResultSet result = null;

        try {
            result = stmt.executeQuery();

            if (result.next()) {

                productIngredient.setIngredientId(result.getInt("ingredientId"));
                productIngredient.setRawMatId(result.getInt("rawMatId"));
                productIngredient.setProductId(result.getInt("productId"));

            } else {
                // System.out.println("ProductIngredient Object Not Found!");
                throw new NotFoundException("ProductIngredient Object Not Found!");
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
                ProductIngredient temp = createproductIngredient();

                temp.setIngredientId(result.getInt("ingredientId"));
                temp.setRawMatId(result.getInt("rawMatId"));
                temp.setProductId(result.getInt("productId"));

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
