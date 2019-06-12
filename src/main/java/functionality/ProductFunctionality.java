package functionality;

import dal.ProductDAO;
import dal.interfaces.IProductDAO;
import dto.interfaces.IProduct;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;

public class ProductFunctionality {
    private IProductDAO productDAO;
    private IDatabaseConnection dbc;

    public ProductFunctionality(){
        dbc = new DatabaseConnection();
        productDAO = new ProductDAO();
    }

    public void createProduct(IProduct prod){
        try {
            Connection conn = dbc.getConnection();
        }catch (Exception e){

        }
    }
}
