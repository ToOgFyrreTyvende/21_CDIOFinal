package functionality;

import dal.WeighedIngredientsBatchesDAO;
import dal.interfaces.IWeighedIngredientsBatchesDAO;
import services.DatabaseConnection;
import services.IDatabaseConnection;

public class WeighingFunctionality {
    private IWeighedIngredientsBatchesDAO wibDAO;
    private IDatabaseConnection dbc;

    public WeighingFunctionality(){
        dbc = new DatabaseConnection();
        wibDAO = new WeighedIngredientsBatchesDAO();
    }

}
