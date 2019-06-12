package functionality.interfaces;

import dto.interfaces.IProductIngredient;

import java.util.List;

public interface IIngredientsFunctionality {
    void createIngredient(IProductIngredient ingredient) throws Exception;

    IProductIngredient getIngredient(int prodingID) throws Exception;

    List<IProductIngredient> getAllIngredient() throws Exception;

    void updateIngredient(IProductIngredient proding) throws Exception;

    void deleteIngredient(IProductIngredient proding) throws Exception;
}
