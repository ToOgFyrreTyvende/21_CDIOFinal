package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;
import dal.exceptions.NotFoundException;

public class RawMat implements dto.interfaces.IRawMat {

      private int rawMatID;
      private String rawMatName;

      public RawMat() {
      }

      public RawMat(int rawMatIDIn) {

            this.rawMatID = rawMatIDIn;

      }

      @Override
      public int getRawMatID() {
            return this.rawMatID;
      }

      @Override
      public void setRawMatID(int rawMatIDIn) {
            this.rawMatID = rawMatIDIn;
      }

      @Override
      public String getRawMatName() {
            return this.rawMatName;
      }

      @Override
      public void setRawMatName(String rawMatNameIn) {
            this.rawMatName = rawMatNameIn;
      }

      @Override
      public void setAll(int rawMatIDIn, String rawMatNameIn) {
            this.rawMatID = rawMatIDIn;
            this.rawMatName = rawMatNameIn;
      }

      @Override
      public boolean hasEqualMapping(RawMat valueObject) {

            if (valueObject.getRawMatID() != this.rawMatID) {
                  return (false);
            }
            if (this.rawMatName == null) {
                  if (valueObject.getRawMatName() != null)
                        return (false);
            } else if (!this.rawMatName.equals(valueObject.getRawMatName())) {
                  return (false);
            }

            return true;
      }

      public String toString() {
            StringBuffer out = new StringBuffer();
            out.append("\nclass RawMat, mapping to table RawMats\n");
            out.append("Persistent attributes: \n");
            out.append("rawMatID = " + this.rawMatID + "\n");
            out.append("rawMatName = " + this.rawMatName + "\n");
            return out.toString();
      }

      public Object clone() {
            RawMat cloned = new RawMat();

            cloned.setRawMatID(this.rawMatID);
            if (this.rawMatName != null)
                  cloned.setRawMatName(new String(this.rawMatName));
            return cloned;
      }
}