package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class RawMatBatch implements Cloneable, Serializable {

    private int rmbId;
    private String rawMatId;
    private double amount;
    private String supplier;

    public RawMatBatch() {
    }

    public RawMatBatch(int rmbIdIn) {
        this.rmbId = rmbIdIn;
    }

    public int getRmbId() {
        return this.rmbId;
    }

    public void setRmbId(int rmbIdIn) {
        this.rmbId = rmbIdIn;
    }

    public String getRawMatId() {
        return this.rawMatId;
    }

    public void setRawMatId(String rawMatIdIn) {
        this.rawMatId = rawMatIdIn;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amountIn) {
        this.amount = amountIn;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public void setSupplier(String supplierIn) {
        this.supplier = supplierIn;
    }

    public void setAll(int rmbIdIn, String rawMatIdIn, double amountIn, String supplierIn) {
        this.rmbId = rmbIdIn;
        this.rawMatId = rawMatIdIn;
        this.amount = amountIn;
        this.supplier = supplierIn;
    }

    public boolean hasEqualMapping(RawMatBatch valueObject) {

        if (valueObject.getRmbId() != this.rmbId) {
            return (false);
        }
        if (this.rawMatId == null) {
            if (valueObject.getRawMatId() != null)
                return (false);
        } else if (!this.rawMatId.equals(valueObject.getRawMatId())) {
            return (false);
        }
        if (valueObject.getAmount() != this.amount) {
            return (false);
        }
        if (this.supplier == null) {
            if (valueObject.getSupplier() != null)
                return (false);
        } else if (!this.supplier.equals(valueObject.getSupplier())) {
            return (false);
        }
        return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass RawMatBatch, mapping to table RawMatBatches\n");
        out.append("Persistent attributes: \n");
        out.append("rmbId = " + this.rmbId + "\n");
        out.append("rawMatId = " + this.rawMatId + "\n");
        out.append("amount = " + this.amount + "\n");
        out.append("supplier = " + this.supplier + "\n");
        return out.toString();
    }

    public Object clone() {
        RawMatBatch cloned = new RawMatBatch();

        cloned.setRmbId(this.rmbId);
        if (this.rawMatId != null)
            cloned.setRawMatId(new String(this.rawMatId));
        cloned.setAmount(this.amount);
        if (this.supplier != null)
            cloned.setSupplier(new String(this.supplier));
        return cloned;
    }
}
