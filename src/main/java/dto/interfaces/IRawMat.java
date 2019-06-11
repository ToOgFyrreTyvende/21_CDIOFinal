package dto.interfaces;

import dto.RawMat;

import java.io.Serializable;

public interface IRawMat extends Cloneable, Serializable {
    int getRawMatID();

    void setRawMatID(int rawMatIDIn);

    String getRawMatName();

    void setRawMatName(String rawMatNameIn);

    void setAll(int rawMatIDIn, String rawMatNameIn);

    boolean hasEqualMapping(RawMat valueObject);
}
