package functionality;

import dal.ProductDAO;
import dal.interfaces.IDAO;
import dto.interfaces.IProduct;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class ProductFunctionality implements functionality.interfaces.IFunctionality<IProduct> {
    private IDAO<IProduct> productDAO;
    private IDatabaseConnection dbc;

    public ProductFunctionality() {
        dbc = new DatabaseConnection();
        productDAO = new ProductDAO();
    }

    @Override
    public void createDTO(IProduct prod) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.create(conn, prod);
        } catch (Exception e){
            throw new Exception("Could not create product with ingredients!");
        }
    }

    @Override
    public IProduct getDTO(int prodId) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return productDAO.getObject(conn, prodId);
        } catch (Exception e){
            throw new Exception("Could not get product with ID: " + prodId);
        }
    }

    @Override
    public List<IProduct> getAllDTOs() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return productDAO.loadAll(conn);
        } catch (Exception e){
            throw new Exception("Could not get all products");
        }
    }

    @Override
    public void updateDTO(IProduct product) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.save(conn, product);
        } catch (Exception e){
            throw new Exception("Could not update given product");
        }
    }

    @Override
    public void deleteDTO(IProduct product) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            productDAO.delete(conn, product);
        } catch (Exception e){
            throw new Exception("Could not delete given product");
        }
    }
}
