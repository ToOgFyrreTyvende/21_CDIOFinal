package dto.interfaces;

import dto.User;

public interface IUser {
    int getUserId();

    void setUserId(int userIdIn);

    String getUserName();

    void setUserName(String userNameIn);

    String getIni();

    void setIni(String iniIn);

    String getCpr();

    void setCpr(String cprIn);

    String getRole();

    void setRole(String roleIn);

    void setAll(int userIdIn,
                String userNameIn,
                String iniIn,
                String cprIn,
                String roleIn);

    boolean hasEqualMapping(User valueObject);
}
