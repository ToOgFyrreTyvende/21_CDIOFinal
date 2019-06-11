package dto;

import java.io.*;

public class Product implements Cloneable, Serializable {

    private int productId;
    private String productName;
    private int rawMatId;
    private double nomNetto;
    private double tolerance;

    public Product() {

    }

    public Product(int productIdIn) {

        this.productId = productIdIn;

    }

    public int getProductId() {
        return this.productId;
    }
    public void setProductId(int productIdIn) {
        this.productId = productIdIn;
    }

    public String getProductName() {
        return this.productName;
    }
    public void setProductName(String productNameIn) {
        this.productName = productNameIn;
    }

    public int getRawMatId() {
        return this.rawMatId;
    }
    public void setRawMatId(int rawMatIdIn) {
        this.rawMatId = rawMatIdIn;
    }

    public double getNomNetto() {
        return this.nomNetto;
    }
    public void setNomNetto(double nomNettoIn) {
        this.nomNetto = nomNettoIn;
    }

    public double getTolerance() {
        return this.tolerance;
    }
    public void setTolerance(double toleranceIn) {
        this.tolerance = toleranceIn;
    }

    public void setAll(int productIdIn,
                       String productNameIn,
                       int rawMatIdIn,
                       double nomNettoIn,
                       double toleranceIn) {
        this.productId = productIdIn;
        this.productName = productNameIn;
        this.rawMatId = rawMatIdIn;
        this.nomNetto = nomNettoIn;
        this.tolerance = toleranceIn;
    }

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
        if (valueObject.getRawMatId() != this.rawMatId) {
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

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass Product, mapping to table Product\n");
        out.append("Persistent attributes: \n");
        out.append("productId = " + this.productId + "\n");
        out.append("productName = " + this.productName + "\n");
        out.append("rawMatId = " + this.rawMatId + "\n");
        out.append("nomNetto = " + this.nomNetto + "\n");
        out.append("tolerance = " + this.tolerance + "\n");
        return out.toString();
    }

    public Object clone() {
        Product cloned = new Product();

        cloned.setProductId(this.productId);
        if (this.productName != null)
            cloned.setProductName(new String(this.productName));
        cloned.setRawMatId(this.rawMatId);
        cloned.setNomNetto(this.nomNetto);
        cloned.setTolerance(this.tolerance);
        return cloned;
    }

}