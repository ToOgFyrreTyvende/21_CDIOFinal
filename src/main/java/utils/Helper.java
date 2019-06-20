package utils;

import models.Product;
import models.RawMatBatch;

public class Helper {
    public static Product.RawMatAmount rawMatInProduct(Product product, RawMatBatch rawMatBatch){
        for (Product.RawMatAmount ing:product.getIngredients()) {
            if (ing.getRawMatId() == rawMatBatch.getRawMatId()){
                return ing;
            }
        }
        return null;
    }

    public static double getAmount(Product product, RawMatBatch rawMatBatch){
        Product.RawMatAmount rawmat = rawMatInProduct(product, rawMatBatch);
        if(rawmat != null)
            return rawmat.getAmount();

        return 0.0;
    }

    public static double getTolerance(Product product, RawMatBatch rawMatBatch){
        Product.RawMatAmount rawmat = rawMatInProduct(product, rawMatBatch);
        if(rawmat != null)
            return rawmat.getTolerance();

        return 0.0;
    }

    public static boolean isRawMatInProduct(Product product, RawMatBatch rawMatBatch){
        Product.RawMatAmount rawmat = rawMatInProduct(product, rawMatBatch);
        if(rawmat != null)
            return true;
        return false;
    }

    public static double lowerBound(double tolerance, double value) {
        return value - ( value * (tolerance / 100));
    }

    public static double higherBound(double tolerance, double value) {
        return value + ( value * (tolerance / 100));
    }
}
