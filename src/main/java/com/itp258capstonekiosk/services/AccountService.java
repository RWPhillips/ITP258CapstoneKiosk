package com.itp258capstonekiosk.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import com.itp258capstonekiosk.objects.AccountObject;

public class AccountService {

	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private KioskDbUtil database;
	private LoginService login;

	public AccountService(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public boolean createAccount(String userName, String userPassword, String confirmPassword, int accountTypeID) {

	    // For checking if the account was created
	    boolean accountCreated = false;

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

	        // Check if the account already exists
	        String checkAccountQuery = "SELECT * FROM accounts WHERE username = ?";
	        statement = connection.prepareStatement(checkAccountQuery);
	        statement.setString(1, userName);
	        resultSet = statement.executeQuery();

	        if (!resultSet.next()) {

	        	if (userPassword.equals(confirmPassword)) {
	        	
		        	// Hash the password
		            String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());     
	
		            // The account doesn't exist, create it
		            String createAccountQuery = "INSERT INTO accounts (userName, userPassword, accountTypeID) VALUES (?, ?, ?)";
		            statement = connection.prepareStatement(createAccountQuery);
		            statement.setString(1, userName);
		            statement.setString(2, hashedPassword);
		            statement.setInt(3, accountTypeID);
	
		            int rowsInserted = statement.executeUpdate();
		            if (rowsInserted > 0) {
		                accountCreated = true;
		            }
	        	}
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, statement, resultSet);
	    }

	    return accountCreated;
	}
	
	public ArrayList<AccountObject> getAccounts(String userName) {
	    
        ArrayList<AccountObject> accountList = new ArrayList<AccountObject>();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

	        // Check if the account already exists
	        String checkAccountQuery = "SELECT userName FROM accounts";
	        statement = connection.prepareStatement(checkAccountQuery);
	        resultSet = statement.executeQuery();
	        
	       /* if (!resultSet.next()) {
	        	// If no accounts
	        	System.out.println("No Accounts Found!");
	        }*/
	        
	        // Go through results
	        while (resultSet.next())
	        {
	        	// Add account to list
	        	AccountObject acc = new AccountObject(resultSet.getString(1));
	        	accountList.add(acc);
	        }


	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, statement, resultSet);
	    }

	    // Return accounts
	    return accountList;
	}
	
	public String deleteAccount(String userName, String password) {
		
		// Testing!
		System.out.println("Account to Delete: " + userName);
		
		// Status string
		String status = "";
		
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

		    // Validate the user name and hashed password against the database
		    login = new LoginService(dataSource);
		    boolean validLogin = login.validateUser(userName, password);

		    if (validLogin) {
		    	
		        // Check for account
		        String deleteAccountQuery = "DELETE FROM accounts WHERE userName = ?";
		        statement = connection.prepareStatement(deleteAccountQuery);
		        statement.setString(1, userName);
		        int rowsAffected = statement.executeUpdate();
	
		        if (rowsAffected > 0) {
		        	// If account found
		        	status = "Account Deleted!";
		        }
		        else {
		        	status = "Something went wrong. Try again later.";
		        }
		    }
		    else {
		    	status = "Invalid Login or Account Doesn't Exist!";
		    }
		    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, statement, resultSet);
	    }
	    
	    return status;
	    
	}
	
public String updatePassword(String userName, String userPassword, String newPassword) {
		
		// Testing!
		System.out.println("Account to Update: " + userName);
		
		// Status string
		String status = "";
		
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

		    // Validate the user name and hashed password against the database
		    login = new LoginService(dataSource);
		    boolean validLogin = login.validateUser(userName, userPassword);

		    if (validLogin) {
		    	
		    	// Hash the password
	            String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt()); 
		    	
		        // Check for account
	            String updatePasswordQuery = "UPDATE accounts SET userPassword = ? WHERE userName = ?";
		        statement = connection.prepareStatement(updatePasswordQuery);
		        
		        // Update to new hashed password
		        statement.setString(1, hashedPassword);
		        statement.setString(2, userName);
		        int rowsAffected = statement.executeUpdate();
	
		        if (rowsAffected > 0) {
		        	// If account found
		        	status = "Password Changed!";
		        }
		        else {
		        	status = "Something went wrong. Try again later.";
		        }
		    }
		    else {
		    	status = "Invalid Login or Account Doesn't Exist!";
		    }
		    
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, statement, resultSet);
	    }
	    
	    return status;
	    
	}

}
