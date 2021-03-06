package functionality;

import dal.ProductBatchDAO;
import dal.interfaces.IDAO;
import dto.interfaces.IProductBatch;
import services.*;

import java.sql.Connection;
import java.util.List;

public class ProductBatchFunctionality implements functionality.interfaces.IFunctionality<IProductBatch> {
    private IDAO<IProductBatch> prodBatchDAO;
    private IDatabaseConnection dbc;

    public ProductBatchFunctionality() {
        dbc = new DatabaseConnection();
        prodBatchDAO = new ProductBatchDAO();
    }

    @Override
    public void createDTO(IProductBatch prodBatch) throws Exception {
        try {
            if (prodBatch.getStatus() < 0 || prodBatch.getStatus() > 2) {
                throw new Exception("Status on product batch is off; not within range 0-2 (inclusive)");
            }
            Connection conn = dbc.getConnection();
            prodBatchDAO.create(conn, prodBatch);
        } catch (Exception e){
            throw new Exception("Could not create product batch: " + e.getMessage());
        }
    }

    @Override
    public IProductBatch getDTO(int pBatchId) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodBatchDAO.getObject(conn, pBatchId);
        } catch (Exception e){
            throw new Exception("Could not get product batch: " + e.getMessage());
        }
    }

    @Override
    public List<IProductBatch> getAllDTOs() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return prodBatchDAO.loadAll(conn);
        } catch (Exception e){
            throw new Exception("Could not get list of product batches: " + e.getMessage());
        }
    }

    @Override
    public void updateDTO(IProductBatch prodBatch) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            if (prodBatch.getStatus() == 0 && prodBatch.getWeighings().length > 0)
                prodBatch.setStatus(1);
            prodBatchDAO.save(conn, prodBatch);
        } catch (Exception e){
            throw new Exception("Could not update product batch: " + e.getMessage());
        }
    }

    @Override
    public void deleteDTO(IProductBatch prodBatch) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            prodBatchDAO.delete(conn, prodBatch);
        } catch (Exception e){
            throw new Exception("Could not delete product batch: " + e.getMessage());
        }
    }
}
