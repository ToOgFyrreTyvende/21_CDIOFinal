package dto.interfaces;

import dto.Product;

import java.util.List;

public interface IProduct {
    int getProductId();

    void setProductId(int productIdIn);

    String getProductName();

    void setProductName(String productNameIn);

    List<IProduct.IRawMatAmount> getIngredients();

    void setIngredients(List<IProduct.IRawMatAmount> ingredients);

    void setAll(int productIdIn,
                String productNameIn);

    boolean hasEqualMapping(Product valueObject);

    interface IRawMatAmount {
        int getRawMatId();

        void setRawMatId(int rawMatId);

        String getName();

        void setName(String name);

        double getAmount();

        void setAmount(double amount);

        double getTolerance();

        void setTolerance(double toleranceIn);

    }
}
