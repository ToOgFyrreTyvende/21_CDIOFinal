package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class ProductIngredient implements Cloneable, Serializable {

    private int ingredientId;
    private int rawMatId;
    private int productId;


    public ProductIngredient () {}

    public ProductIngredient (int ingredientIdIn) {
        this.ingredientId = ingredientIdIn;
    }

    public int getIngredientId() {
        return this.ingredientId;
    }
    public void setIngredientId(int ingredientIdIn) {
        this.ingredientId = ingredientIdIn;
    }

    public int getRawMatId() {
        return this.rawMatId;
    }
    public void setRawMatId(int rawMatIdIn) {
        this.rawMatId = rawMatIdIn;
    }

    public int getProductId() {
        return this.productId;
    }
    public void setProductId(int productIdIn) {
        this.productId = productIdIn;
    }


    public void setAll(int ingredientIdIn,
                       int rawMatIdIn,
                       int productIdIn) {
        this.ingredientId = ingredientIdIn;
        this.rawMatId = rawMatIdIn;
        this.productId = productIdIn;
    }


    public boolean hasEqualMapping(ProductIngredient valueObject) {

        if (valueObject.getIngredientId() != this.ingredientId) {
            return(false);
        }
        if (valueObject.getRawMatId() != this.rawMatId) {
            return(false);
        }
        if (valueObject.getProductId() != this.productId) {
            return(false);
        }

        return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass ProductIngredient, mapping to table ProductIngredients\n");
        out.append("Persistent attributes: \n");
        out.append("ingredientId = " + this.ingredientId + "\n");
        out.append("rawMatId = " + this.rawMatId + "\n");
        out.append("productId = " + this.productId + "\n");
        return out.toString();
    }

    public Object clone() {
        ProductIngredient cloned = new ProductIngredient();

        cloned.setIngredientId(this.ingredientId);
        cloned.setRawMatId(this.rawMatId);
        cloned.setProductId(this.productId);
        return cloned;
    }

}
