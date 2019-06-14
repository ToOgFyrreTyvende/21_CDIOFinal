package utils;

import models.Product;
import models.RawMatBatch;

public class Helper {
    public static double getAmount(Product product, RawMatBatch rawMatBatch){
        for (Product.RawMatAmount ing:product.getIngredients()) {
            if (ing.getRawMatId() == rawMatBatch.getRawMatId()){
                return ing.getAmount();
            }
        }
        return 0.0;
    }

    public static boolean rawMatInProduct(Product product, RawMatBatch rawMatBatch){
        for (Product.RawMatAmount ing:product.getIngredients()) {
            if (ing.getRawMatId() == rawMatBatch.getRawMatId()){
                return true;
            }
        }
        return false;
    }
}
