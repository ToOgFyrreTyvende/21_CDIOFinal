package models;

public class ProductBatch {

    private String name;
    private int prodBatchId;
    private int prodId;
    private int status;
    private Weighing[] weighings;

    public ProductBatch() {
    }

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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int statusIn) {
        this.status = statusIn;
    }

    public void setAll(int prodBatchIdIn, int prodIdIn, int rawMatBatchIdIn, int statusIn) {
        this.prodBatchId = prodBatchIdIn;
        this.prodId = prodIdIn;
        this.status = statusIn;
    }
    public Weighing[] getWeighings() {
        return weighings;
    }
    public void setWeighings(Weighing[] weighings) {
        this.weighings = weighings;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass ProductBatchDAO, mapping to table ProductBatches\n");
        out.append("Persistent attributes: \n");
        out.append("prodBatchId = " + this.prodBatchId + "\n");
        out.append("prodId = " + this.prodId + "\n");
        out.append("status = " + this.status + "\n");
        return out.toString();
    }

    public static class Weighing {
        private int rmbId;
        private int rawMatId;
        private int userId;
        private String name;
        private double tara;
        private double netto;
        private String supplier;

        public int getRmbId() {
            return rmbId;
        }
        public void setRmbId(int rmbId) {
            this.rmbId = rmbId;
        }
        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRawMatId() {
            return rawMatId;
        }
        public void setRawMatId(int rawMatId) {
            this.rawMatId = rawMatId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public double getTara() {
            return tara;
        }
        public void setTara(double tara) {
            this.tara = tara;
        }
        public double getNetto() {
            return netto;
        }
        public void setNetto(double netto) {
            this.netto = netto;
        }
        public String getSupplier() {
            return supplier;
        }
        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }
    }
}
