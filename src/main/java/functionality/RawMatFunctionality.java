package functionality;

import dal.RawMatDAO;
import dal.interfaces.IRawMatDAO;
import dto.interfaces.IRawMat;
import services.DatabaseConnection;
import services.IDatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class RawMatFunctionality implements functionality.interfaces.IRawMatFunctionality {
    private IRawMatDAO rawMatDAO;
    private IDatabaseConnection dbc;

    public RawMatFunctionality() {
        dbc = new DatabaseConnection();
        rawMatDAO = new RawMatDAO();
    }

    @Override
    public void createRawMat(IRawMat rawmat) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatDAO.create(conn, rawmat);
        } catch (Exception e){
            throw new Exception("Could not create raw mat");
        }
    }

    @Override
    public IRawMat getRawMat(int rawmatid) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return rawMatDAO.getObject(conn, rawmatid);
        } catch (Exception e){
            throw new Exception("Could not get raw mat with ID: " + rawmatid);
        }
    }

    @Override
    public List<IRawMat> getAllRawMats() throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return rawMatDAO.loadAll(conn);
        } catch (Exception e){
            throw new Exception("Could not get all raw mats");
        }
    }

    @Override
    public void updateRawMat(IRawMat rawmat) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatDAO.save(conn, rawmat);
        } catch (Exception e){
            throw new Exception("Could not update given raw mat");
        }
    }

    @Override
    public void deleteRawMat(IRawMat rawmat) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            rawMatDAO.delete(conn, rawmat);
        } catch (Exception e){
            throw new Exception("Could not delete raw mat");
        }
    }
}
