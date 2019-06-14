package models;

public class Weighing {

    private int weighedIngredientId;
    private int rawMatBatchId;
    private int prodBatchId;
    private int userId;
    private double tara;
    private double netto;

    public Weighing() {
    }

    public Weighing(int rawMatBatchId, int prodBatchId, int userId, double tara, double netto) {

        this.rawMatBatchId = rawMatBatchId;
        this.prodBatchId = prodBatchId;
        this.userId = userId;
        this.tara = tara;
        this.netto = netto;

    }

    public int getWeighedIngredientId() {
        return this.weighedIngredientId;
    }

    public void setWeighedIngredientId(int weighedBatchIdIn) {
        this.weighedIngredientId = weighedBatchIdIn;
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

    public int getProdBatchId() {
        return prodBatchId;
    }
    public void setProdBatchId(int prodBatchId) {
        this.prodBatchId = prodBatchId;
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
}