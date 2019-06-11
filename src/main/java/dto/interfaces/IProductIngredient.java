package dto.interfaces;

import dto.ProductIngredient;

import java.io.Serializable;

public interface IProductIngredient extends Cloneable, Serializable {
    int getIngredientId();

    void setIngredientId(int ingredientIdIn);

    int getRawMatId();

    void setRawMatId(int rawMatIdIn);

    int getProductId();

    void setProductId(int productIdIn);

    void setAll(int ingredientIdIn,
                int rawMatIdIn,
                int productIdIn);

    boolean hasEqualMapping(ProductIngredient valueObject);
}
