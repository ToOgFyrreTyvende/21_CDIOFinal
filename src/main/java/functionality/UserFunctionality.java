package functionality;

import dal.UserDAO;
import dal.interfaces.IUserDAO;
import dto.interfaces.*;
import services.*;

import java.sql.Connection;
import java.util.List;


public class UserFunctionality implements functionality.interfaces.IUserFunctionality {
    private IUserDAO userDAO;
    private IDatabaseConnection dbc;

    public UserFunctionality(){
        dbc = new DatabaseConnection();
        userDAO = new UserDAO();
    }

    @Override
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

    @Override
    public IUser getUser(int userID)throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return userDAO.getObject(conn, userID);
        } catch (Exception e) {
            throw new Exception("Could not find user: " + e.getMessage());
        }
    }

    @Override
    public List<IUser> getAllUsers()throws Exception {
        try {
            Connection conn = dbc.getConnection();
            return userDAO.loadAll(conn);
        } catch (Exception e) {
            throw new Exception("Could not find user: " + e.getMessage());
        }
    }

    @Override
    public void updateUser(IUser user) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            userDAO.save(conn, user);
        } catch (Exception e) {
            throw new Exception("Could not update user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(IUser user) throws Exception {
        try {
            Connection conn = dbc.getConnection();
            userDAO.delete(conn, user);
        } catch (Exception e) {
            throw new Exception("Could not delete user: " + e.getMessage());
        }
    }
}
