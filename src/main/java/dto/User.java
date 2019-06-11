package dto;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class User implements Cloneable, Serializable {

    private int userId;
    private String userName;
    private String ini;
    private String cpr;
    private String role;

    public User () {}

    public User (int userIdIn) {
          this.userId = userIdIn;
    }

    public int getUserId() {
          return this.userId;
    }
    public void setUserId(int userIdIn) {
          this.userId = userIdIn;
    }

    public String getUserName() {
          return this.userName;
    }
    public void setUserName(String userNameIn) {
          this.userName = userNameIn;
    }

    public String getIni() {
          return this.ini;
    }
    public void setIni(String iniIn) {
          this.ini = iniIn;
    }

    public String getCpr() {
          return this.cpr;
    }
    public void setCpr(String cprIn) {
          this.cpr = cprIn;
    }

    public String getRole() {
          return this.role;
    }
    public void setRole(String roleIn) {
          this.role = roleIn;
    }

    public void setAll(int userIdIn,
          String userNameIn,
          String iniIn,
          String cprIn,
          String roleIn) {
          this.userId = userIdIn;
          this.userName = userNameIn;
          this.ini = iniIn;
          this.cpr = cprIn;
          this.role = roleIn;
    }

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
          if (this.role == null) {
                    if (valueObject.getRole() != null)
                           return(false);
          } else if (!this.role.equals(valueObject.getRole())) {
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
        if (this.role != null)
             cloned.setRole(new String(this.role)); 
        return cloned;
    }
}