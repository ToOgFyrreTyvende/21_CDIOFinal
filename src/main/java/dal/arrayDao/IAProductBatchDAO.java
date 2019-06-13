package dal.arrayDao;

import java.util.List;
import dto.interfaces.IProductBatch;

public interface IAProductBatchDAO {

    IProductBatch getProdBat(int prodbatId);
    List<IProductBatch> getProdBatList();
    void createProdBat(IProductBatch prodbat);
    void updateProdBat(IProductBatch prodbat);
    void deleteProdBat(int prodbatId);
}
