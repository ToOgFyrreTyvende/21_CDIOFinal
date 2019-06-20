package dto;

import dto.interfaces.IProductBatch;
import java.util.*;

public class ProductBatch implements dto.interfaces.IProductBatch {

    private int prodBatchId;
    private String name;
    private int prodId;
    private int status;
    private ProductBatch.Weighings[] weighings;

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
    public ProductBatch.Weighings[] getWeighings() {
        return weighings;
    }
    @Override
    public void setWeighings(ProductBatch.Weighings[] weighings) {
        this.weighings = weighings;
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

    public static class Weighings implements IProductBatch.IWeighings {
        private int rmbId;
        private int rawMatId;
        private int userId;
        private String name;
        private double tara;
        private double netto;
        private String supplier;

        @Override
        public int getRmbId() {
            return rmbId;
        }
        @Override
        public void setRmbId(int rmbId) {
            this.rmbId = rmbId;
        }
        @Override
        public int getUserId() {
            return userId;
        }
        @Override
        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public int getRawMatId() {
            return rawMatId;
        }
        @Override
        public void setRawMatId(int rawMatId) {
            this.rawMatId = rawMatId;
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
        public double getTara() {
            return tara;
        }
        @Override
        public void setTara(double tara) {
            this.tara = tara;
        }
        @Override
        public double getNetto() {
            return netto;
        }
        @Override
        public void setNetto(double netto) {
            this.netto = netto;
        }
        @Override
        public String getSupplier() {
            return supplier;
        }
        @Override
        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }
    }
}
