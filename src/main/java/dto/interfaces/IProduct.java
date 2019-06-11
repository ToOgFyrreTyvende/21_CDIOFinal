package dto.interfaces;

import dto.Product;

import java.util.List;

public interface IProduct {
    int getProductId();

    void setProductId(int productIdIn);

    String getProductName();

    void setProductName(String productNameIn);

    double getNomNetto();

    void setNomNetto(double nomNettoIn);

    double getTolerance();

    void setTolerance(double toleranceIn);

    List<IProduct.IRawMatAmount> getIngredients();

    void setIngredients(List<IProduct.IRawMatAmount> ingredients);

    void setAll(int productIdIn,
                String productNameIn,
                int rawMatIdIn,
                double nomNettoIn,
                double toleranceIn);

    boolean hasEqualMapping(Product valueObject);

    interface IRawMatAmount {
        int getRawMatId();

        void setRawMatId(int rawMatId);

        String getName();

        void setName(String name);

        double getAmount();

        void setAmount(double amount);
    }
}
