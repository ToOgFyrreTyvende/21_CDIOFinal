package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class User implements Cloneable, Serializable, dto.interfaces.IUser {

    private int userId;
    private String userName;
    private String ini;
    private String cpr;
    /*
    0 = Admin,
    1 = Laborant,
    2 = Produktionsleder,
    3 = Farmaceut
     */
    private int role;

    public User () {}

    public User (int userIdIn) {
          this.userId = userIdIn;
    }

    @Override
    public int getUserId() {
          return this.userId;
    }
    @Override
    public void setUserId(int userIdIn) {
          this.userId = userIdIn;
    }

    @Override
    public String getUserName() {
          return this.userName;
    }
    @Override
    public void setUserName(String userNameIn) {
          this.userName = userNameIn;
    }

    @Override
    public String getIni() {
          return this.ini;
    }
    @Override
    public void setIni(String iniIn) {
          this.ini = iniIn;
    }

    @Override
    public String getCpr() {
          return this.cpr;
    }
    @Override
    public void setCpr(String cprIn) {
          this.cpr = cprIn;
    }

    @Override
    public int getRole() {
          return this.role;
    }
    @Override
    public void setRole(int roleIn) {
          this.role = roleIn;
    }

    @Override
    public void setAll(int userIdIn,
                       String userNameIn,
                       String iniIn,
                       String cprIn,
                       int roleIn) {
          this.userId = userIdIn;
          this.userName = userNameIn;
          this.ini = iniIn;
          this.cpr = cprIn;
          this.role = roleIn;
    }

    @Override
    public boolean hasEqualMapping(User valueObject) {

          if (valueObject.getUserId() != this.userId) {
                    return(false);
          }
          if (this.userName == null) {
                    if (valueObject.getUserName() != null)
                           return(false);
          } else if (!this.userName.equals(valueObject.getUserName())) {
                    return(false);
          }
          if (this.ini == null) {
                    if (valueObject.getIni() != null)
                           return(false);
          } else if (!this.ini.equals(valueObject.getIni())) {
                    return(false);
          }
          if (this.cpr == null) {
                    if (valueObject.getCpr() != null)
                           return(false);
          } else if (!this.cpr.equals(valueObject.getCpr())) {
                    return(false);
          }
          if (this.role == 0) {
              return(false);
          }

          return true;
    }

    public String toString() {
        StringBuffer out = new StringBuffer();
        out.append("\nclass User, mapping to table Users\n");
        out.append("Persistent attributes: \n"); 
        out.append("userId = " + this.userId + "\n"); 
        out.append("userName = " + this.userName + "\n"); 
        out.append("ini = " + this.ini + "\n"); 
        out.append("cpr = " + this.cpr + "\n"); 
        out.append("role = " + this.role + "\n"); 
        return out.toString();
    }

    public Object clone() {
        User cloned = new User();

        cloned.setUserId(this.userId); 
        if (this.userName != null)
             cloned.setUserName(new String(this.userName)); 
        if (this.ini != null)
             cloned.setIni(new String(this.ini)); 
        if (this.cpr != null)
             cloned.setCpr(new String(this.cpr)); 
        if (this.role != 0)
             cloned.setRole(this.role);
        return cloned;
    }
}