package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class WeighedBatch implements Cloneable, Serializable, dto.interfaces.IWeighedBatch {

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

    @java.lang.Override
    public int getWeighedBatchId() {
        return this.weighedBatchId;
    }

    @java.lang.Override
    public void setWeighedBatchId(int weighedBatchIdIn) {
        this.weighedBatchId = weighedBatchIdIn;
    }

    @java.lang.Override
    public int getRawMatBatchId() {
        return this.rawMatBatchId;
    }

    @java.lang.Override
    public void setRawMatBatchId(int rawMatBatchIdIn) {
        this.rawMatBatchId = rawMatBatchIdIn;
    }

    @java.lang.Override
    public int getUserId() {
        return this.userId;
    }

    @java.lang.Override
    public void setUserId(int userIdIn) {
        this.userId = userIdIn;
    }

    @java.lang.Override
    public double getTara() {
        return this.tara;
    }

    @java.lang.Override
    public void setTara(double taraIn) {
        this.tara = taraIn;
    }

    @java.lang.Override
    public double getNetto() {
        return this.netto;
    }

    @java.lang.Override
    public void setNetto(double nettoIn) {
        this.netto = nettoIn;
    }

    @java.lang.Override
    public void setAll(int weighedBatchIdIn, int rawMatBatchIdIn, int userIdIn, double taraIn, double nettoIn) {
        this.weighedBatchId = weighedBatchIdIn;
        this.rawMatBatchId = rawMatBatchIdIn;
        this.userId = userIdIn;
        this.tara = taraIn;
        this.netto = nettoIn;
    }

    @java.lang.Override
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