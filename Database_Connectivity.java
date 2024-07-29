package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database_Connectivity {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/atm";
	 private static final String USER = "root";
     private static final String PASSWORD = "Akshat21";
     
     public static Connection getConnection() throws SQLException {
         return DriverManager.getConnection(URL, USER, PASSWORD);
     }
}
