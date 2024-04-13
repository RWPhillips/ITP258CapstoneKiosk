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
	private AccountObject account;

	public AccountService(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public boolean createAccount(String userName, String userPassword, int accountTypeID) {

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
	        
	        while (resultSet.next())
	        {
	        	AccountObject acc = new AccountObject(userName);
	        	accountList.add(acc);
	        }

	        if (!resultSet.next()) {

	        	System.out.println("No Accounts Found!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, statement, resultSet);
	    }

	    return accountList;
	}

}
