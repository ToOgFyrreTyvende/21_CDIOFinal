package dto.interfaces;

import dto.Product;

public interface IProduct {
    int getProductId();

    void setProductId(int productIdIn);

    String getProductName();

    void setProductName(String productNameIn);

    double getNomNetto();

    void setNomNetto(double nomNettoIn);

    double getTolerance();

    void setTolerance(double toleranceIn);

    void setAll(int productIdIn,
                String productNameIn,
                int rawMatIdIn,
                double nomNettoIn,
                double toleranceIn);

    boolean hasEqualMapping(Product valueObject);
}
