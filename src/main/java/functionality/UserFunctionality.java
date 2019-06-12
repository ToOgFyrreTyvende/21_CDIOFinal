package functionality;

import dal.UserDAO;
import dal.interfaces.IUserDAO;
import dto.interfaces.*;
import services.*;

import java.sql.Connection;
import java.util.List;


public class UserFunctionality implements IUserFunctionality {
    private IUserDAO userDAO;
    private IDatabaseConnection dbc;

    public UserFunctionality(){
        dbc = new DatabaseConnection();
        userDAO = new UserDAO();
    }

    public void createUser(IUser user) throws Exception {
        try {
            if (user.getUserName().length() < 2 && user.getUserName().length() > 20) {
                throw new Exception("Username is too short/long.");
            }
            if (user.getIni().length() < 2 && user.getIni().length() > 4) {
                throw new Exception("Initials is too short/long.");
            }
            if (user.getCpr().length() != 10 && user.getCpr().charAt(6) != '-') {
                throw new Exception("CPR is not correctly input.");
            }
            Connection conn = dbc.getConnection();
            userDAO.create(conn, user);
        } catch (Exception e){
            throw new Exception("Could not create user: " + e.getMessage());
        }
    }


}
