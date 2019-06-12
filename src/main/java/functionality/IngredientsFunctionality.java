package functionality;

import dal.ProductIngredientDAO;
import dal.interfaces.IProductIngredientDAO;
import dto.interfaces.IProductIngredient;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class IngredientsFunctionality implements functionality.interfaces.IIngredientsFunctionality {
    private IProductIngredientDAO prodingDAO;
    private IDatabaseConnection dbc;

    public IngredientsFunctionality(){
        prodingDAO = new ProductIngredientDAO();
        dbc = new DatabaseConnection();
    }

    @Override
    public void createIngredient(IProductIngredient ingredient) throws Exception {
        try {
            if(ingredient.getAmount() <= 0) {
                throw new Exception("Amount is incorrect.");
            }
            if (ingredient.getProductId() == 0) {
                throw new Exception("Product doesn't exist.");
            }
            if (ingredient.getTolerance() <= 0) {
                throw new Exception("Tolerance is incorrect");
            }
            if (ingredient.getRawMatId() == 0) {
                throw new Exception("Raw material doesn't exist.");
            }
            if (ingredient.getProductIngredientId() == 0) {
                throw new Exception("Product ingredient is incorrect");
            }
            Connection conn = dbc.getConnection();
            prodingDAO.create(conn, ingredient);
        } catch (Exception e) {
            throw new Exception("Can't create ingredient: " + e.getMessage());
        }
    }

    @Override
    public IProductIngredient getIngredient(int prodingID) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodingDAO.getObject(conn,prodingID);
        } catch (Exception e) {
            throw new Exception("Can't get ingredient: " + e.getMessage());
        }

    }

    @Override
    public List<IProductIngredient> getAllIngredient() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodingDAO.loadAll(conn);
        } catch (Exception e) {
            throw new Exception("Can't get all ingredient: " + e.getMessage());
        }
    }

    @Override
    public void updateIngredient(IProductIngredient proding) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            prodingDAO.load(conn,proding);
        } catch (Exception e) {
            throw new Exception("Can't update ingredient: " + e.getMessage());
        }
    }

    @Override
    public void deleteIngredient(IProductIngredient proding) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            prodingDAO.delete(conn,proding);
        } catch (Exception e) {
            throw new Exception("Can't delete ingredient: " + e.getMessage());
        }
    }
}
