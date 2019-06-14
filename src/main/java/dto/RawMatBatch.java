package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class RawMatBatch implements dto.interfaces.IRawMatBatch {

    private int rmbId;
    private int rawMatId;
    private double amount;
    private String supplier;
    private String name;

    public RawMatBatch() {
    }

    public RawMatBatch(int rmbIdIn) {
        this.rmbId = rmbIdIn;
    }

    @Override
    public int getRmbId() {
        return this.rmbId;
    }

    @Override
    public void setRmbId(int rmbIdIn) {
        this.rmbId = rmbIdIn;
    }

    @Override
    public int getRawMatId() {
        return this.rawMatId;
    }

    @Override
    public void setRawMatId(int rawMatIdIn) {
        this.rawMatId = rawMatIdIn;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }

    @Override
    public void setAmount(double amountIn) {
        this.amount = amountIn;
    }

    @Override
    public String getSupplier() {
        return this.supplier;
    }

    @Override
    public void setSupplier(String supplierIn) {
        this.supplier = supplierIn;
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
    public void setAll(int rmbIdIn, int rawMatIdIn, double amountIn, String supplierIn) {
        this.rmbId = rmbIdIn;
        this.rawMatId = rawMatIdIn;
        this.amount = amountIn;
        this.supplier = supplierIn;
    }

    @Override
    public boolean hasEqualMapping(RawMatBatch valueObject) {

        if (valueObject.getRmbId() != this.rmbId) {
            return (false);
        }
        if (valueObject.getRawMatId() != this.rawMatId) {
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
        cloned.setRawMatId(this.rawMatId);
        cloned.setAmount(this.amount);
        if (this.supplier != null)
            cloned.setSupplier(new String(this.supplier));
        return cloned;
    }
}
