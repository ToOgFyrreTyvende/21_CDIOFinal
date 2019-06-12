package functionality;

import dal.interfaces.IUserDAO;
import dto.*;

import java.util.List;


public class UserFunctionality implements IUserFunctionality {
    private IUserDAO userDAO;

    public UserFunctionality(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

}
