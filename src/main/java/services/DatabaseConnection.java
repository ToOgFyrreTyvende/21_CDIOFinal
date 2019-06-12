package services;

import utils.SQLTools;

import java.sql.Connection;

public class DatabaseConnection implements IDatabaseConnection {

    public Connection getConnection(){
        return SQLTools.createConnection();
    }
}
