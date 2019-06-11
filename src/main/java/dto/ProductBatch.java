package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class ProductBatch implements dto.interfaces.IProductBatch {

    private int prodBatchId;
    private int prodId;
    private int status;

    public ProductBatch() {
    }

    public ProductBatch(int prodBatchIdIn) {

        this.prodBatchId = prodBatchIdIn;

    }

    @Override
    public int getProdBatchId() {
        return this.prodBatchId;
    }

    @Override
    public void setProdBatchId(int prodBatchIdIn) {
        this.prodBatchId = prodBatchIdIn;
    }

    @Override
    public int getProdId() {
        return this.prodId;
    }

    @Override
    public void setProdId(int prodIdIn) {
        this.prodId = prodIdIn;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(int statusIn) {
        this.status = statusIn;
    }

    @Override
    public void setAll(int prodBatchIdIn, int prodIdIn, int rawMatBatchIdIn, int statusIn) {
        this.prodBatchId = prodBatchIdIn;
        this.prodId = prodIdIn;
        this.status = statusIn;
    }

    @Override
    public boolean hasEqualMapping(ProductBatch valueObject) {

        if (valueObject.getProdBatchId() != this.prodBatchId) {
            return (false);
        }
        if (valueObject.getProdId() != this.prodId) {
            return (false);
        }
        if (valueObject.getStatus() != this.status) {
            return (false);
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass ProductBatchDAO, mapping to table ProductBatches\n");
        out.append("Persistent attributes: \n");
        out.append("prodBatchId = " + this.prodBatchId + "\n");
        out.append("prodId = " + this.prodId + "\n");
        out.append("status = " + this.status + "\n");
        return out.toString();
    }

    @Override
    public Object clone() {
        ProductBatch cloned = new ProductBatch();

        cloned.setProdBatchId(this.prodBatchId);
        cloned.setProdId(this.prodId);
        cloned.setStatus(this.status);
        return cloned;
    }
}
