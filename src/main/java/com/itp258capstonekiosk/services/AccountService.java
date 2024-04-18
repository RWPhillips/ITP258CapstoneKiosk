package com.itp258capstonekiosk.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;

import com.itp258capstonekiosk.objects.AccountObject;

public class AccountService {

	private DataSource dataSource;
	private Connection connection;
	private CallableStatement callableStatement;
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
		    callableStatement = connection.prepareCall("{CALL checkAccount(?)}");
		    callableStatement.setString(1, userName);
	        resultSet = callableStatement.executeQuery();

	        if (!resultSet.next()) {

	        	if (userPassword.equals(confirmPassword)) {

		        	// Hash the password
		            String hashedPassword = BCrypt.hashpw(userPassword, BCrypt.gensalt());

		            // The account doesn't exist, create it
		            callableStatement = connection.prepareCall("{CALL createAccount(?, ?, ?)}");
		            callableStatement.setString(1, userName);
		            callableStatement.setString(2, hashedPassword);
		            callableStatement.setInt(3, accountTypeID);

		            int rowsInserted = callableStatement.executeUpdate();
		            if (rowsInserted > 0) {
		                accountCreated = true;
		            }
	        	}
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }

	    return accountCreated;
	}

	public ArrayList<AccountObject> getAccounts(String userName) {

        ArrayList<AccountObject> accountList = new ArrayList<>();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

	        // Check if the account already exists
		    callableStatement = connection.prepareCall("{CALL selectUsers()}");
	        resultSet = callableStatement.executeQuery();
	        int count = 0;

	        // Go through results
	        while (resultSet.next())
	        {
	        	// Add account to list
	        	AccountObject acc = new AccountObject(resultSet.getString(1));
	        	accountList.add(acc);
	        	System.out.println(acc.getUsername());
	        }
	        
	        System.out.println(count);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
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
		        callableStatement = connection.prepareCall("{CALL deleteAccount(?)}");
		        callableStatement.setString(1, userName);
		        int rowsAffected = callableStatement.executeUpdate();

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
	        database.closeConnection(connection, callableStatement, resultSet);
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
	            callableStatement = connection.prepareCall("{CALL updatePassword(?, ?)}");

		        // Update to new hashed password
	            callableStatement.setString(1, userName);
	            callableStatement.setString(2, hashedPassword);
		        int rowsAffected = callableStatement.executeUpdate();

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
	        database.closeConnection(connection, callableStatement, resultSet);
	    }

	    return status;

	}

}
