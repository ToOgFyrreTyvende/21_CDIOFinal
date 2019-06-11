package dto.interfaces;

import dto.WeighedBatch;

public interface IWeighedBatch {
    int getWeighedBatchId();

    void setWeighedBatchId(int weighedBatchIdIn);

    int getRawMatBatchId();

    void setRawMatBatchId(int rawMatBatchIdIn);

    int getUserId();

    void setUserId(int userIdIn);

    double getTara();

    void setTara(double taraIn);

    double getNetto();

    void setNetto(double nettoIn);

    void setAll(int weighedBatchIdIn, int rawMatBatchIdIn, int userIdIn, double taraIn, double nettoIn);

    boolean hasEqualMapping(WeighedBatch valueObject);
}
