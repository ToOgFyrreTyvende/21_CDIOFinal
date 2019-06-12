package dto;

import dto.interfaces.IWeighedIngredientsBatches;

import java.io.*;

public class WeighedIngredientsBatches implements Cloneable, Serializable, IWeighedIngredientsBatches {

    private int weighedIngredientId;
    private int rawMatBatchId;
    private int prodBatchId;
    private int userId;
    private double tara;
    private double netto;

    public WeighedIngredientsBatches() {
    }

    public WeighedIngredientsBatches(int weighedBatchIdIn) {

        this.weighedIngredientId = weighedBatchIdIn;

    }

    @Override
    public int getWeighedIngredientId() {
        return this.weighedIngredientId;
    }

    @Override
    public void setWeighedIngredientId(int weighedBatchIdIn) {
        this.weighedIngredientId = weighedBatchIdIn;
    }

    @Override
    public int getRawMatBatchId() {
        return this.rawMatBatchId;
    }

    @Override
    public void setRawMatBatchId(int rawMatBatchIdIn) {
        this.rawMatBatchId = rawMatBatchIdIn;
    }

    @Override
    public int getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(int userIdIn) {
        this.userId = userIdIn;
    }

    @Override
    public double getTara() {
        return this.tara;
    }

    @Override
    public void setTara(double taraIn) {
        this.tara = taraIn;
    }

    @Override
    public double getNetto() {
        return this.netto;
    }

    @Override
    public void setNetto(double nettoIn) {
        this.netto = nettoIn;
    }

    @Override
    public int getProdBatchId() {
        return prodBatchId;
    }
    @Override
    public void setProdBatchId(int prodBatchId) {
        this.prodBatchId = prodBatchId;
    }

    @Override
    public void setAll(int weighedBatchIdIn, int rawMatBatchIdIn, int userIdIn, double taraIn, double nettoIn) {
        this.weighedIngredientId = weighedBatchIdIn;
        this.rawMatBatchId = rawMatBatchIdIn;
        this.userId = userIdIn;
        this.tara = taraIn;
        this.netto = nettoIn;
    }

    @Override
    public boolean hasEqualMapping(WeighedIngredientsBatches valueObject) {

        if (valueObject.getWeighedIngredientId() != this.weighedIngredientId) {
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
        out.append("\nclass WeighedIngredientsBatches, mapping to table WeighedBatches\n");
        out.append("Persistent attributes: \n");
        out.append("weighedIngredientId = " + this.weighedIngredientId + "\n");
        out.append("rawMatBatchId = " + this.rawMatBatchId + "\n");
        out.append("userId = " + this.userId + "\n");
        out.append("tara = " + this.tara + "\n");
        out.append("netto = " + this.netto + "\n");
        return out.toString();
    }

    public Object clone() {
        WeighedIngredientsBatches cloned = new WeighedIngredientsBatches();

        cloned.setWeighedIngredientId(this.weighedIngredientId);
        cloned.setRawMatBatchId(this.rawMatBatchId);
        cloned.setUserId(this.userId);
        cloned.setTara(this.tara);
        cloned.setNetto(this.netto);
        return cloned;
    }

}