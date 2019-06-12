package functionality.interfaces;

import dto.interfaces.IRawMat;

import java.util.List;

public interface IRawMatFunctionality {
    void createRawMat(IRawMat rawmat) throws Exception;

    IRawMat getRawMat(int rawmatid) throws Exception;

    List<IRawMat> getAllRawMats() throws Exception;

    void updateRawMat(IRawMat rawmat) throws Exception;

    void deleteRawMat(IRawMat rawmat) throws Exception;
}
