package functionality.interfaces;

import dto.interfaces.IRawMatBatch;

import java.util.List;

public interface IRawMatBatchFunctionality {
    void createRawMatBatch(IRawMatBatch rawMatBatch) throws Exception;

    IRawMatBatch getRawMatBatch(int rawmatbatchid) throws Exception;

    List<IRawMatBatch> getAllRawMatBatches() throws Exception;

    void updateRawMatBatch(IRawMatBatch rawMatBatch) throws Exception;

    void deleteRawMatBatch(IRawMatBatch rawMatBatch) throws Exception;
}
