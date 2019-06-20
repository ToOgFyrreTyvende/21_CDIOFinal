package functionality;

import dal.ProductIngredientDAO;
import dal.interfaces.IDAO;
import dto.interfaces.IProductIngredient;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class IngredientsFunctionality implements functionality.interfaces.IFunctionality<IProductIngredient> {
    private IDAO<IProductIngredient> prodIngDAO;
    private IDatabaseConnection dbc;

    public IngredientsFunctionality(){
        prodIngDAO = new ProductIngredientDAO();
        dbc = new DatabaseConnection();
    }

    @Override
    public void createDTO(IProductIngredient ingredient) throws Exception {
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
            prodIngDAO.create(conn, ingredient);
        } catch (Exception e) {
            throw new Exception("Can't create ingredient: " + e.getMessage());
        }
    }

    @Override
    public IProductIngredient getDTO(int prodIngID) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodIngDAO.getObject(conn, prodIngID);
        } catch (Exception e) {
            throw new Exception("Can't get ingredient: " + e.getMessage());
        }

    }

    @Override
    public List<IProductIngredient> getAllDTOs() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodIngDAO.loadAll(conn);
        } catch (Exception e) {
            throw new Exception("Can't get all ingredient: " + e.getMessage());
        }
    }

    @Override
    public void updateDTO(IProductIngredient prodIng) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            prodIngDAO.load(conn, prodIng);
        } catch (Exception e) {
            throw new Exception("Can't update ingredient: " + e.getMessage());
        }
    }

    @Override
    public void deleteDTO(IProductIngredient prodIng) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            prodIngDAO.delete(conn, prodIng);
        } catch (Exception e) {
            throw new Exception("Can't delete ingredient: " + e.getMessage());
        }
    }
}
