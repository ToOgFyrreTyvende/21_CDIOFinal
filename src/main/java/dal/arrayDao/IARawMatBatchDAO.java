package dal.arrayDao;

import java.util.List;
import dto.interfaces.IRawMatBatch;

public interface IARawMatBatchDAO {

    IRawMatBatch getRawMatBat(int rawmatbatId);
    List<IRawMatBatch> getRawMatBatList();
    void createRawMatBat(IRawMatBatch rawmatbat);
    void updateRawMatBat(IRawMatBatch rawmatbat);
    void deleteRawMatBat(int rawmatbatId);
}

