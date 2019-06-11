package dto.interfaces;

import dto.RawMatBatch;

import java.io.Serializable;

public interface IRawMatBatch extends Cloneable, Serializable {
    int getRmbId();

    void setRmbId(int rmbIdIn);

    int getRawMatId();

    void setRawMatId(int rawMatIdIn);

    double getAmount();

    void setAmount(double amountIn);

    String getSupplier();

    void setSupplier(String supplierIn);

    void setAll(int rmbIdIn, int rawMatIdIn, double amountIn, String supplierIn);

    boolean hasEqualMapping(RawMatBatch valueObject);
}
