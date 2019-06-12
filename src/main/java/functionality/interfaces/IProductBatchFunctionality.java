package functionality.interfaces;

import dto.interfaces.IProductBatch;

import java.util.List;

public interface IProductBatchFunctionality {
    void createProductBatch(IProductBatch prodBatch) throws Exception;
    IProductBatch getProdBatch(int pBatchId) throws Exception;
    List<IProductBatch> getAllProdBatches() throws Exception;
    void updateProdBatch(IProductBatch prodBatch) throws Exception;
    void deleteProdBatch(IProductBatch prodBatch)throws Exception;
}
