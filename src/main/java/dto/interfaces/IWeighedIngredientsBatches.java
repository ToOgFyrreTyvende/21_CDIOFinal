package dto.interfaces;

import dto.WeighedIngredientsBatches;

public interface IWeighedIngredientsBatches {
    int getWeighedIngredientId();

    void setWeighedIngredientId(int weighedBatchIdIn);

    int getRawMatBatchId();

    void setRawMatBatchId(int rawMatBatchIdIn);

    int getUserId();

    void setUserId(int userIdIn);

    double getTara();

    void setTara(double taraIn);

    double getNetto();

    void setNetto(double nettoIn);

    int getProdBatchId();

    void setProdBatchId(int prodBatchId);

    void setAll(int weighedBatchIdIn, int rawMatBatchIdIn, int userIdIn, double taraIn, double nettoIn);

    boolean hasEqualMapping(WeighedIngredientsBatches valueObject);
}
