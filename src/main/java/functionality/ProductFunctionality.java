package functionality;

import dal.ProductDAO;
import dal.interfaces.IProductDAO;
import dto.Product;
import dto.interfaces.IProduct;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ProductFunctionality implements functionality.interfaces.IProductFunctionality {
    private IProductDAO productDAO;
    private IDatabaseConnection dbc;

    public ProductFunctionality(){
        dbc = new DatabaseConnection();
        productDAO = new ProductDAO();
    }

    @Override
    public void createProduct(IProduct prod) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.create(conn, prod);
        }catch (Exception e){
            throw new Exception("Could not create product with ingredients!");
        }
    }

    @Override
    public IProduct getProduct(int prodId) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return productDAO.getObject(conn, prodId);
        }catch (Exception e){
            throw new Exception("Could not get product with ID: " + prodId);
        }
    }

    @Override
    public List<IProduct> getAllProducts() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return productDAO.loadAll(conn);
        }catch (Exception e){
            throw new Exception("Could not get all products with");
        }
    }

    @Override
    public void updateProduct(IProduct product) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.save(conn, product);
        }catch (Exception e){
            throw new Exception("Could not update given product");
        }
    }

    @Override
    public void deleteProduct(IProduct product) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.delete(conn, product);
        }catch (Exception e){
            throw new Exception("Could not delete given product");
        }
    }
}
