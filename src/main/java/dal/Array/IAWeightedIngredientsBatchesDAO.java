package dal.Array;

import java.util.List;
import dto.interfaces.IWeighedIngredientsBatches;

public interface IAWeightedIngredientsBatchesDAO {

    IWeighedIngredientsBatches getWIB(int wibId);
    List<IWeighedIngredientsBatches> getWIBList();
    void createWIB(IWeighedIngredientsBatches wib);
    void updateWIB(IWeighedIngredientsBatches prod);
    void deleteWIB(int wibId);
}

