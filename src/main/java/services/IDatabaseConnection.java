package services;

import java.sql.Connection;

public interface IDatabaseConnection {
    Connection getConnection();
}
