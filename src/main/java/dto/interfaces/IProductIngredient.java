package dto.interfaces;

import dto.ProductIngredient;

import java.io.Serializable;

public interface IProductIngredient extends Cloneable, Serializable {
    int getProductIngredientId();

    void setProductIngredientId(int ingredientIdIn);

    int getRawMatId();

    void setRawMatId(int rawMatIdIn);

    int getProductId();

    void setProductId(int productIdIn);

    int getAmount();

    void setAmount(int amount);

    void setAll(int ingredientIdIn,
                int rawMatIdIn,
                int productIdIn);

    boolean hasEqualMapping(ProductIngredient valueObject);
}
