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

    double getAmount();

    void setAmount(double amount);

    double getTolerance();

    void setTolerance(double tolerance);

    void setAll(int ingredientIdIn,
                int rawMatIdIn,
                int productIdIn,
                double amountin,
                double tolerancein);

    boolean hasEqualMapping(ProductIngredient valueObject);
}
