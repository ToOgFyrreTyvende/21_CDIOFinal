package dto.interfaces;

import dto.ProductBatch;

import java.io.Serializable;

public interface IProductBatch extends Cloneable, Serializable {
    int getProdBatchId();

    void setProdBatchId(int prodBatchIdIn);

    int getProdId();

    void setProdId(int prodIdIn);

    int getStatus();

    void setStatus(int statusIn);

    void setAll(int prodBatchIdIn, int prodIdIn, int rawMatBatchIdIn, int statusIn);

    boolean hasEqualMapping(ProductBatch valueObject);
}
