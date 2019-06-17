package functionality.interfaces;

import dto.interfaces.IWeighedIngredientsBatches;

import java.util.List;

public interface IWeighingFunctionality {
    void createWeighing(IWeighedIngredientsBatches ingredient) throws Exception;

    IWeighedIngredientsBatches getWeighing(int weighingID) throws Exception;

    List<IWeighedIngredientsBatches> getAllWeighings() throws Exception;

    void updateWeighing(IWeighedIngredientsBatches weighing) throws Exception;

    void deleteWeighing(IWeighedIngredientsBatches weighing) throws Exception;
}
