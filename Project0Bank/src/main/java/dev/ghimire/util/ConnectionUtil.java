package dev.ghimire.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    static Logger logger = Logger.getLogger(ConnectionUtil.class.getName());
    public static Connection getConnection()
    {
        try
        {
           // String connectionString = "jdbc:postgresql://host/BankAPIProject0DB?user=username&password=password";
            String connectionString =System.getenv("CONNECTION_STRING");
            Connection conn = DriverManager.getConnection(connectionString);
            return conn;
        }catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            logger.fatal("the connection couldn't be established",sqlException);
            return null;
        }


    }


}
