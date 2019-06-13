package dal.arrayDao;

import java.util.List;
import dto.interfaces.IWeighedIngredientsBatches;

public interface IAWeightedIngredientsBatchesDAO {

    IWeighedIngredientsBatches getWIB(int wibId);
    List<IWeighedIngredientsBatches> getWIBList();
    void createWIB(IWeighedIngredientsBatches wib);
    void updateWIB(IWeighedIngredientsBatches wib);
    void deleteWIB(int wibId);
}

