package com.itp258capstonekiosk.services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.CategoryObject;
import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;

public class ItemService {

	private DataSource dataSource;
	private Connection connection;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	private KioskDbUtil database;

	public ItemService(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public ItemObject createItem(int categoryID, String name, double cost, int imageID, String description, String categoryTags) {

	    ItemObject item = new ItemObject();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewItem(?, ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, categoryID);
            callableStatement.setString(2, name);
            callableStatement.setDouble(3, cost);
            callableStatement.setInt(4, imageID);
            callableStatement.setString(5, description);
            callableStatement.setString(6, categoryTags);

            // Create item
            item = new ItemObject(resultSet.getString(1), resultSet.getString(2), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDouble(3));

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }

	    return item;
	}

	public SubItemObject createSubItem(String name, double cost, int categoryID) {

	    SubItemObject subItem = new SubItemObject();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewSubItem(?, ?, ?)}");
            callableStatement.setString(1, name);
            callableStatement.setDouble(2, cost);
            callableStatement.setInt(3, categoryID);

            // Create item
            subItem = new SubItemObject(resultSet.getString(1), resultSet.getDouble(2), resultSet.getInt(3));

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }

	    return subItem;
	}

	public void createCategory(String name, String url) {

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // create a new category
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewCategory(?, ?)}");
            callableStatement.setString(1, name);
            callableStatement.setString(2, url);
            callableStatement.execute();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}

	public void createSubCategory(String name) {
			//System.out.println("inside csub"); 
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewSubCategory(?)}");
            callableStatement.setString(1, name);
            callableStatement.execute(); 

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> strings = new ArrayList<>(); 
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL getCategory()}");
	        resultSet = callableStatement.executeQuery();

	        //iterate over the result set adding each string to the array
	        while (resultSet.next()) {
	        	String cat = resultSet.getString(1); 
	        	strings.add(cat); 
	        	//System.out.println(cat);
	        }
	        
	        return strings; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return strings; 
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}
	public void deleteCategory(String category) {

		// Testing!
		//System.out.println("Category to Delete: " + category);
		
		 try {

			    // Connect to database
			    database = new KioskDbUtil(dataSource);
			    connection = database.getConnection();

		        // delete the category
		        callableStatement = connection.prepareCall("{CALL deleteCategory(?)}");
		        callableStatement.setString(1, category);
		        callableStatement.execute(); 

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close JDBC objects
		        database.closeConnection(connection, callableStatement, resultSet);
		    }

	}
	public void deleteSubcategory(String subCategory) {
		
		 try {

			    // Connect to database
			    database = new KioskDbUtil(dataSource);
			    connection = database.getConnection();

		        // delete the subCategory
		        callableStatement = connection.prepareCall("{CALL deleteSubItemCategory(?)}");
		        callableStatement.setString(1, subCategory);
		        callableStatement.execute(); 

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close JDBC objects
		        database.closeConnection(connection, callableStatement, resultSet);
		    }
	}
	public ArrayList<String> getSubCategories() {
		ArrayList<String> strings = new ArrayList<>(); 
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL getSubCategories()}");
	        resultSet = callableStatement.executeQuery();

	        //iterate over the result set adding each string to the array
	        while (resultSet.next()) {
	        	String cat = resultSet.getString(1); 
	        	strings.add(cat); 
	        	//System.out.println(cat);
	        }
	        
	        return strings; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return strings; 
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}
	
	public ArrayList<CategoryObject> getFullCategory() {
        ArrayList<CategoryObject> categories = new ArrayList<>();
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get category details
            //PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            // Leaving it in case we need it ?
            
            CallableStatement callableStatement = connection.prepareCall("{CALL getCategoryNameImage()}");
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            // Process the result set
            while (resultSet.next()) {
            	String name = resultSet.getString(1);
                String imageURL = resultSet.getString(2);
                
                
                // Create CategoryObject instance
                CategoryObject category = new CategoryObject(imageURL, name);
                
                // Add category to the ArrayList
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return categories;
    }
	
}

