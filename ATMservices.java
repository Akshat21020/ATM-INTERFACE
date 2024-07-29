package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMservices {
	
	// Balance
	public double getBalance(int userId) {
        String query = "SELECT balance FROM accounts WHERE user_id = ?";
        try (Connection conn = Database_Connectivity.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
	
	//deposit
	 public boolean deposit(int userId, double amount) {
	        return updateBalance(userId, amount,"DEPOSIT");
	    }
	 
	 //withdraw
	    public boolean withdraw(int userId, double amount) {
	        return updateBalance(userId, -amount,"WITHDRAWAL");
	    }

	    private boolean updateBalance(int userId, double amount,String transaction_type) {
	        String updateQuery = "UPDATE accounts SET balance = balance + ? WHERE user_id = ?";
	        String transactionQuery = "INSERT INTO transactions (account_id, transaction_type, amount) VALUES ((SELECT account_id FROM accounts WHERE user_id = ?), ?, ?)";
	        try (Connection conn = Database_Connectivity.getConnection();
	             PreparedStatement updatePstmt = conn.prepareStatement(updateQuery);
	             PreparedStatement transactionPstmt = conn.prepareStatement(transactionQuery)) {
	            conn.setAutoCommit(false);

	            updatePstmt.setDouble(1, amount);
	            updatePstmt.setInt(2, userId);
	            int rowsAffected = updatePstmt.executeUpdate();

	            transactionPstmt.setInt(1, userId);
	            transactionPstmt.setString(2, transaction_type);
	            transactionPstmt.setDouble(3, amount);
	            transactionPstmt.executeUpdate();

	            conn.commit();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    
}
