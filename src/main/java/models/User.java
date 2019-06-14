package models;

public class User {

    private int userId;
    private String userName;
    private String ini;
    private String cpr;
    private int role;

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

    public int getRole() {
        return this.role;
    }
    public void setRole(int roleIn) {
        this.role = roleIn;
    }

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
}