package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class ProductBatch implements Cloneable, Serializable {

    private int prodBatchId;
    private int prodId;
    private int rawMatBatchId;
    private int status;

    public ProductBatch() {}

    public ProductBatch(int prodBatchIdIn) {

        this.prodBatchId = prodBatchIdIn;

    }


    public int getProdBatchId() {
        return this.prodBatchId;
    }

    public void setProdBatchId(int prodBatchIdIn) {
        this.prodBatchId = prodBatchIdIn;
    }

    public int getProdId() {
        return this.prodId;
    }

    public void setProdId(int prodIdIn) {
        this.prodId = prodIdIn;
    }

    public int getRawMatBatchId() {
        return this.rawMatBatchId;
    }

    public void setRawMatBatchId(int rawMatBatchIdIn) {
        this.rawMatBatchId = rawMatBatchIdIn;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int statusIn) {
        this.status = statusIn;
    }

    public void setAll(int prodBatchIdIn, int prodIdIn, int rawMatBatchIdIn, int statusIn) {
        this.prodBatchId = prodBatchIdIn;
        this.prodId = prodIdIn;
        this.rawMatBatchId = rawMatBatchIdIn;
        this.status = statusIn;
    }

    public boolean hasEqualMapping(ProductBatch valueObject) {

        if (valueObject.getProdBatchId() != this.prodBatchId) {
            return (false);
        }
        if (valueObject.getProdId() != this.prodId) {
            return (false);
        }
        if (valueObject.getRawMatBatchId() != this.rawMatBatchId) {
            return (false);
        }
        if (valueObject.getStatus() != this.status) {
            return (false);
        }

        return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass ProductBatchDAO, mapping to table ProductBatches\n");
        out.append("Persistent attributes: \n");
        out.append("prodBatchId = " + this.prodBatchId + "\n");
        out.append("prodId = " + this.prodId + "\n");
        out.append("rawMatBatchId = " + this.rawMatBatchId + "\n");
        out.append("status = " + this.status + "\n");
        return out.toString();
    }


    public Object clone() {
        ProductBatch cloned = new ProductBatch();

        cloned.setProdBatchId(this.prodBatchId);
        cloned.setProdId(this.prodId);
        cloned.setRawMatBatchId(this.rawMatBatchId);
        cloned.setStatus(this.status);
        return cloned;
    }
}