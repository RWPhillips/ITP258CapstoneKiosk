package com.itp258capstonekiosk.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.SubItemObject;

public class CartService {
	
	private DataSource dataSource;
	private Connection connection;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	private KioskDbUtil database;

	public CartService(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public SubItemObject getSubItem(String name) {
		
        SubItemObject subItem = new SubItemObject();
		
		try {
			// Connect to the database
	        database = new KioskDbUtil(dataSource);
	        connection = database.getConnection();
	        
	        // Prepare the SQL statement to get all items
	        CallableStatement callableStatement = connection.prepareCall("{CALL getSubItemList(?)}");
	        callableStatement.setString(1, name);
	        
	        // Execute the query
	        resultSet = callableStatement.executeQuery();
	
	        // Check for sub item
	        if (resultSet.next()) {
		        String subItemName = resultSet.getString(1);
		        double subItemCost = resultSet.getDouble(2);
		        int subItemCat = resultSet.getInt(3);
		                       
		        // Create ItemObject instance
		        subItem = new SubItemObject(subItemName);
	        }
        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
		
        return subItem;
	}

}
