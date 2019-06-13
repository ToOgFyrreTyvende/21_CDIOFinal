package dal.Array;

import java.util.List;
import dto.interfaces.IUser;

public interface IAUserDAO {

    IUser getUser(int userId);
    List<IUser> getUser();
    void createUser(IUser user);
    void updateUser(IUser user);
    void deleteUser(int userId);
}

