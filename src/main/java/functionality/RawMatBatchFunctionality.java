package functionality;

import dal.RawMatBatchDAO;
import dal.interfaces.IDAO;
import dto.interfaces.IRawMatBatch;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class RawMatBatchFunctionality implements functionality.interfaces.IRawMatBatchFunctionality {
    private IDAO<IRawMatBatch> rawMatBatchDAO;
    private IDatabaseConnection dbc;

    public RawMatBatchFunctionality() {
        dbc = new DatabaseConnection();
        rawMatBatchDAO = new RawMatBatchDAO();
    }

    @Override
    public void createRawMatBatch(IRawMatBatch rawMatBatch) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatBatchDAO.create(conn, rawMatBatch);
        } catch (Exception e){
            throw new Exception("Could not create raw mat batch");
        }
    }

    @Override
    public IRawMatBatch getRawMatBatch(int rawmatbatchid) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return rawMatBatchDAO.getObject(conn, rawmatbatchid);
        } catch (Exception e){
            throw new Exception("Could not get raw mat batch with ID: " + rawmatbatchid);
        }
    }

    @Override
    public List<IRawMatBatch> getAllRawMatBatches() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return rawMatBatchDAO.loadAll(conn);
        } catch (Exception e){
            throw new Exception("Could not get all raw mat batches");
        }
    }

    @Override
    public void updateRawMatBatch(IRawMatBatch rawMatBatch) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatBatchDAO.save(conn, rawMatBatch);
        } catch (Exception e){
            throw new Exception("Could not update given raw mat batch");
        }
    }

    @Override
    public void deleteRawMatBatch(IRawMatBatch rawMatBatch) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatBatchDAO.delete(conn, rawMatBatch);
        } catch (Exception e){
            throw new Exception("Could not delete raw mat batch");
        }
    }
}
