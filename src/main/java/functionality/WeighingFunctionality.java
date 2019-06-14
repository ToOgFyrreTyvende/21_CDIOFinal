package functionality;

import dal.WeighedIngredientsBatchesDAO;
import dal.interfaces.IWeighedIngredientsBatchesDAO;
import dto.interfaces.IWeighedIngredientsBatches;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class WeighingFunctionality implements functionality.interfaces.IWeighingFunctionality {
    private IWeighedIngredientsBatchesDAO wibDAO;
    private IDatabaseConnection dbc;

    public WeighingFunctionality(){
        dbc = new DatabaseConnection();
        wibDAO = new WeighedIngredientsBatchesDAO();
    }

    @Override
    public void createWeighing(IWeighedIngredientsBatches ingredient) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            wibDAO.create(conn, ingredient);
        } catch (Exception e) {
            throw new Exception("Can't create weighing: " + e.getMessage());
        }
    }

    @Override
    public IWeighedIngredientsBatches getWeighing(int weighingId) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return wibDAO.getObject(conn,weighingId);
        } catch (Exception e) {
            throw new Exception("Can't get weighing: " + e.getMessage());
        }

    }

    @Override
    public List<IWeighedIngredientsBatches> getAllWeighings() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return wibDAO.loadAll(conn);
        } catch (Exception e) {
            throw new Exception("Can't get all weighing: " + e.getMessage());
        }
    }

    @Override
    public void updateWeighing(IWeighedIngredientsBatches weighing) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            wibDAO.load(conn,weighing);
        } catch (Exception e) {
            throw new Exception("Can't update weighing: " + e.getMessage());
        }
    }

    @Override
    public void deleteWeighing(IWeighedIngredientsBatches weighing) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            wibDAO.delete(conn,weighing);
        } catch (Exception e) {
            throw new Exception("Can't delete weighing: " + e.getMessage());
        }
    }

}
