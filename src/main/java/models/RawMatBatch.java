package models;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class RawMatBatch {

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

    public int getRmbId() {
        return this.rmbId;
    }

    public void setRmbId(int rmbIdIn) {
        this.rmbId = rmbIdIn;
    }

    public int getRawMatId() {
        return this.rawMatId;
    }

    public void setRawMatId(int rawMatIdIn) {
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAll(int rmbIdIn, int rawMatIdIn, double amountIn, String supplierIn) {
        this.rmbId = rmbIdIn;
        this.rawMatId = rawMatIdIn;
        this.amount = amountIn;
        this.supplier = supplierIn;
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

}
