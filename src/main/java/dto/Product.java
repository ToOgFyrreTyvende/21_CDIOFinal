package dto;

import dto.interfaces.IProduct;
import dto.interfaces.IProductIngredient;
import dto.interfaces.IRawMat;

import java.io.*;
import java.util.List;

public class Product implements IProduct {

    private int productId;
    private String productName;
    private double nomNetto;
    private double tolerance;

    private List<IProduct.IRawMatAmount> ingredients;

    public Product() {

    }

    public Product(int productIdIn) {

        this.productId = productIdIn;

    }

    @Override
    public int getProductId() {
        return this.productId;
    }
    @Override
    public void setProductId(int productIdIn) {
        this.productId = productIdIn;
    }

    @Override
    public String getProductName() {
        return this.productName;
    }
    @Override
    public void setProductName(String productNameIn) {
        this.productName = productNameIn;
    }

    @Override
    public double getNomNetto() {
        return this.nomNetto;
    }
    @Override
    public void setNomNetto(double nomNettoIn) {
        this.nomNetto = nomNettoIn;
    }

    @Override
    public double getTolerance() {
        return this.tolerance;
    }
    @Override
    public void setTolerance(double toleranceIn) {
        this.tolerance = toleranceIn;
    }

    @Override
    public List<IProduct.IRawMatAmount> getIngredients() {
        return ingredients;
    }
    @Override
    public void setIngredients(List<IProduct.IRawMatAmount> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public void setAll(int productIdIn,
                       String productNameIn,
                       int rawMatIdIn,
                       double nomNettoIn,
                       double toleranceIn) {
        this.productId = productIdIn;
        this.productName = productNameIn;
        this.nomNetto = nomNettoIn;
        this.tolerance = toleranceIn;
    }

    @Override
    public boolean hasEqualMapping(Product valueObject) {

        if (valueObject.getProductId() != this.productId) {
            return(false);
        }
        if (this.productName == null) {
            if (valueObject.getProductName() != null)
                return(false);
        } else if (!this.productName.equals(valueObject.getProductName())) {
            return(false);
        }
        if (valueObject.getNomNetto() != this.nomNetto) {
            return(false);
        }
        if (valueObject.getTolerance() != this.tolerance) {
            return(false);
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass Product, mapping to table Product\n");
        out.append("Persistent attributes: \n");
        out.append("productId = " + this.productId + "\n");
        out.append("productName = " + this.productName + "\n");
        out.append("nomNetto = " + this.nomNetto + "\n");
        out.append("tolerance = " + this.tolerance + "\n");
        return out.toString();
    }

    @Override
    public Object clone() {
        Product cloned = new Product();

        cloned.setProductId(this.productId);
        if (this.productName != null)
            cloned.setProductName(new String(this.productName));
        cloned.setNomNetto(this.nomNetto);
        cloned.setTolerance(this.tolerance);
        return cloned;
    }

    public static class RawMatAmount implements IProduct.IRawMatAmount{
        private int rawMatId;
        private String name;
        private double amount;

        @Override
        public int getRawMatId() {
            return rawMatId;
        }
        @Override
        public void setRawMatId(int rawMatId) {
            this.rawMatId = rawMatId;
        }
        @Override
        public String getName() {
            return name;
        }
        @Override
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public double getAmount() {
            return amount;
        }
        @Override
        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}