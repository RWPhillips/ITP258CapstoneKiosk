package com.itp258capstonekiosk.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;


public class LoginService {

	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private KioskDbUtil database;
	
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;

    // Constructor
    public LoginService(DataSource dataSource) {
    	this.dataSource = dataSource;
    }

    // Validate user credentials and return true if valid, false otherwise
    public boolean validateUser(String username, String rawPassword) {
    	String storedHashedPassword = getPasswordFromDatabase(username);

        if (storedHashedPassword == null) {
            // User not found
        	System.out.println("No User Found!");
            return false;
        }
        
        // Use BCrypt's checkpw method to compare the provided password with the stored hash
        System.out.println("User Found!");
        return BCrypt.checkpw(rawPassword, storedHashedPassword);
    }

    // Generate a secure token
    public String generateToken(String username) {
    	
    	// Create a random component
        UUID randomUUID = UUID.randomUUID();

        // Combine the user name and random component
        String token = username + randomUUID.toString();

        return token;
    }

    public String getPasswordFromDatabase(String username) {

    	String storedHashedPassword = null;

        try {

        	if (connection == null) {
        		database = new KioskDbUtil(dataSource);
    			connection = database.getConnection();
        	}

            // Define the SQL query to retrieve the hashed password
            String sqlQuery = "SELECT userPassword FROM accounts WHERE userName = ?";
            statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, username);

            // Execute the query and retrieve the result
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the hashed password from the result set
                storedHashedPassword = resultSet.getString("userPassword");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any database-related exceptions
        }

        return storedHashedPassword;
    }
}
