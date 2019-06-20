package dto.interfaces;

import dto.ProductBatch;

import java.io.Serializable;
import java.util.List;

public interface IProductBatch extends Cloneable, Serializable {
    int getProdBatchId();

    void setProdBatchId(int prodBatchIdIn);

    int getProdId();

    void setProdId(int prodIdIn);

    int getStatus();

    void setStatus(int statusIn);

    void setAll(int prodBatchIdIn, int prodIdIn, int rawMatBatchIdIn, int statusIn);

    ProductBatch.Weighings[] getWeighings();

    void setWeighings(ProductBatch.Weighings[] weighings);

    String getName();

    void setName(String name);

    boolean hasEqualMapping(ProductBatch valueObject);

    interface IWeighings {
        int getRmbId();

        void setRmbId(int rmbId);

        int getUserId();

        void setUserId(int userId);

        int getRawMatId();

        void setRawMatId(int rawMatId);

        String getName();

        void setName(String name);

        double getTara();

        void setTara(double tara);

        double getNetto();

        void setNetto(double netto);

        String getSupplier();

        void setSupplier(String supplier);
    }
}
