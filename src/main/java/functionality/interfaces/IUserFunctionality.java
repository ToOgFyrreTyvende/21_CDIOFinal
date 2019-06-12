package functionality.interfaces;

import dto.interfaces.IUser;

import java.util.List;

public interface IUserFunctionality extends functionality.IUserFunctionality {
    void createUser(IUser user) throws Exception;

    IUser getUser(int userID)throws Exception;

    List<IUser> getAllUsers()throws Exception;

    void updateUser(IUser user) throws Exception;

    void deleteUser(IUser user) throws Exception;
}
