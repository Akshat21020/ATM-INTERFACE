package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registeration_and_Login {
	
	public boolean login(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = Database_Connectivity.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean register(String username, String password) {
        String userQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        String accountQuery = "INSERT INTO accounts (user_id, balance) VALUES ((SELECT user_id FROM users WHERE username = ?), 0.00)";
        try (Connection conn = Database_Connectivity.getConnection();
            PreparedStatement userPstmt = conn.prepareStatement(userQuery);
            PreparedStatement accountPstmt = conn.prepareStatement(accountQuery)) {
        	conn.setAutoCommit(false);

            userPstmt.setString(1, username);
            userPstmt.setString(2, password);
            int userRowsAffected = userPstmt.executeUpdate();

            accountPstmt.setString(1, username);
            int accountRowsAffected = accountPstmt.executeUpdate();
            conn.commit();
            return userRowsAffected > 0 && accountRowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        }
}
