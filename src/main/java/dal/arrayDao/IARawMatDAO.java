package dal.arrayDao;

import java.util.List;
import dto.interfaces.IRawMat;

public interface IARawMatDAO {

    IRawMat getRawMat(int rawmatId);
    List<IRawMat> getRawMatList();
    void createRawMat(IRawMat rawmat);
    void updateRawMat(IRawMat rawmat);
    void deleteRawMat(int rawmatId);
}

