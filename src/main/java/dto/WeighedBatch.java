package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class WeighedBatch implements Cloneable, Serializable {

    private int weighedBatchId;
    private int rawMatBatchId;
    private int userId;
    private double tara;
    private double netto;

    public WeighedBatch() {
    }

    public WeighedBatch(int weighedBatchIdIn) {

        this.weighedBatchId = weighedBatchIdIn;

    }

    public int getWeighedBatchId() {
        return this.weighedBatchId;
    }

    public void setWeighedBatchId(int weighedBatchIdIn) {
        this.weighedBatchId = weighedBatchIdIn;
    }

    public int getRawMatBatchId() {
        return this.rawMatBatchId;
    }

    public void setRawMatBatchId(int rawMatBatchIdIn) {
        this.rawMatBatchId = rawMatBatchIdIn;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userIdIn) {
        this.userId = userIdIn;
    }

    public double getTara() {
        return this.tara;
    }

    public void setTara(double taraIn) {
        this.tara = taraIn;
    }

    public double getNetto() {
        return this.netto;
    }

    public void setNetto(double nettoIn) {
        this.netto = nettoIn;
    }

    public void setAll(int weighedBatchIdIn, int rawMatBatchIdIn, int userIdIn, double taraIn, double nettoIn) {
        this.weighedBatchId = weighedBatchIdIn;
        this.rawMatBatchId = rawMatBatchIdIn;
        this.userId = userIdIn;
        this.tara = taraIn;
        this.netto = nettoIn;
    }

    public boolean hasEqualMapping(WeighedBatch valueObject) {

        if (valueObject.getWeighedBatchId() != this.weighedBatchId) {
            return (false);
        }
        if (valueObject.getRawMatBatchId() != this.rawMatBatchId) {
            return (false);
        }
        if (valueObject.getUserId() != this.userId) {
            return (false);
        }
        if (valueObject.getTara() != this.tara) {
            return (false);
        }
        if (valueObject.getNetto() != this.netto) {
            return (false);
        }

        return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass WeighedBatch, mapping to table WeighedBatches\n");
        out.append("Persistent attributes: \n");
        out.append("weighedBatchId = " + this.weighedBatchId + "\n");
        out.append("rawMatBatchId = " + this.rawMatBatchId + "\n");
        out.append("userId = " + this.userId + "\n");
        out.append("tara = " + this.tara + "\n");
        out.append("netto = " + this.netto + "\n");
        return out.toString();
    }

    public Object clone() {
        WeighedBatch cloned = new WeighedBatch();

        cloned.setWeighedBatchId(this.weighedBatchId);
        cloned.setRawMatBatchId(this.rawMatBatchId);
        cloned.setUserId(this.userId);
        cloned.setTara(this.tara);
        cloned.setNetto(this.netto);
        return cloned;
    }

}