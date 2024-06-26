package com.itp258capstonekiosk.services;

import java.sql.Array;
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

	public void createItem(int categoryID, String name, double cost, String imageUrl, String description) {

	    //ItemObject item = new ItemObject();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();
		    
		    //System.out.println(categoryID + " - " + name + " - " + cost + " - " + imageUrl + " - " + description);

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewItem(?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, categoryID);
            callableStatement.setString(2, name);
            callableStatement.setDouble(3, cost);
            callableStatement.setString(4, imageUrl);
            callableStatement.setString(5, description);
            callableStatement.execute();

            // Create item
            //item = new ItemObject(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getString(4), resultSet.getString(5));

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}

	public void createSubItem(String name, double cost, int categoryID) {

	    //SubItemObject subItem = new SubItemObject();

	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL createNewSubItem(?, ?, ?)}");
            callableStatement.setString(1, name);
            callableStatement.setDouble(2, cost);
            callableStatement.setInt(3, categoryID);
            callableStatement.execute();

            // Create item
            //subItem = new SubItemObject(resultSet.getString(1), resultSet.getDouble(2), resultSet.getInt(3));

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
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
	
	public int getSpecificCategory(String name) {
		ArrayList<String> strings = new ArrayList<>(); 
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL getSpecificCategory(?)}");
            callableStatement.setString(1, name);
	        resultSet = callableStatement.executeQuery();

	        int cat = 0;
	        
	        //iterate over the result set adding each string to the array
	        if (resultSet.next()) {
	        	cat = resultSet.getInt(2); 
	        }
	        
	        return cat; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
	    } finally {
	        // Close JDBC objects
	        database.closeConnection(connection, callableStatement, resultSet);
	    }
	}
	
	public int getSpecificSubCategory(String name) {
		ArrayList<String> strings = new ArrayList<>(); 
	    try {

		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();

            // The account doesn't exist, create it
            CallableStatement callableStatement = connection.prepareCall("{CALL getSpecificSubCategory(?)}");
            callableStatement.setString(1, name);
	        resultSet = callableStatement.executeQuery();

	        int cat = 0;
	        
	        //iterate over the result set adding each string to the array
	        if (resultSet.next()) {
	        	cat = resultSet.getInt(2); 
	        }
	        
	        return cat; 

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return 0;
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
	
	public ArrayList<SubItemObject> getSubItemFromCategory(String name) {
        ArrayList<SubItemObject> subItems = new ArrayList<>();
        
        SubItemObject subItem;
        
        // Convert Category to int
        int catName = getSpecificSubCategory(name);
        System.out.println("Got Category: " + name + " which is #" + catName);
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get all items
            CallableStatement callableStatement = connection.prepareCall("{CALL getSubItemList(?)}");
            callableStatement.setInt(1, catName);
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            int count = 0;
            
            // Process the result set
            while (resultSet.next()) {
                String subItemName = resultSet.getString(1);
                
                
                // Create ItemObject instance
                subItem = new SubItemObject(subItemName);
                
                // Add item to the ArrayList
                subItems.add(subItem);
                
                count++;
                System.out.println("Items Retrieved: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return subItems;
    }
	
	public ArrayList<ItemObject> getItemFromCategory(String name) {
        ArrayList<ItemObject> items = new ArrayList<>();
        
        ItemObject item;
        
        // Convert Category to int
        int catName = getSpecificCategory(name);
        System.out.println("Got Category: " + name + " which is #" + catName);
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get all items
            CallableStatement callableStatement = connection.prepareCall("{CALL getItemList(?)}");
            callableStatement.setInt(1, catName);
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            int count = 0;
            
            // Process the result set
            while (resultSet.next()) {
            	int catID = resultSet.getInt(1); 
                String itemName = resultSet.getString(2);
                double cost = resultSet.getDouble(3); 
                String url = resultSet.getString(4);
                String desc = resultSet.getString(5);
                
                
                
                // Create ItemObject instance
                item = new ItemObject(catID, itemName, cost, url, desc);
                
                // Add item to the ArrayList
                items.add(item);
                
                count++;
                System.out.println("Items Retrieved: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return items;
    }
	
	public ArrayList<ItemObject> getItemList() {
        ArrayList<ItemObject> items = new ArrayList<>();
        
        ItemObject item;
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get all items
            CallableStatement callableStatement = connection.prepareCall("{CALL getItemList()}");
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            int count = 0;
            
            // Process the result set
            while (resultSet.next()) {
            	int category = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double cost = resultSet.getDouble(3);
                String description = resultSet.getString(4);
                String picture = resultSet.getString(5);
                
                
                // Create ItemObject instance
                item = new ItemObject(category, name, cost, picture, description);
                
                // Add item to the ArrayList
                items.add(item);
                
                count++;
                System.out.println("Items Retrieved: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return items;
    }
	public void deleteItem(String item) {

		// Testing!
		System.out.println("item to Delete: " + item);
		
		 try {

			    // Connect to database
			    database = new KioskDbUtil(dataSource);
			    connection = database.getConnection();

		        // delete the category
		        callableStatement = connection.prepareCall("{CALL deleteItem(?)}");
		        callableStatement.setString(1, item);
		        callableStatement.execute(); 

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close JDBC objects
		        database.closeConnection(connection, callableStatement, resultSet);
		    }

	}
	public void deleteSubItem(String subItem) {
		// Testing!
		System.out.println("Subitem to Delete: " + subItem);
		 try {

			    // Connect to database
			    database = new KioskDbUtil(dataSource);
			    connection = database.getConnection();

		        // delete the category
		        callableStatement = connection.prepareCall("{CALL deleteSubItem(?)}");
		        callableStatement.setString(1, subItem);
		        callableStatement.execute(); 

		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        // Close JDBC objects
		        database.closeConnection(connection, callableStatement, resultSet);
		    }
	}
	
	public ItemObject getCompleteItem(String itemName) {
		
        ItemObject item = null;
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get item details by name
            callableStatement = connection.prepareCall("{CALL getCompleteItem(?)}");
            callableStatement.setString(1, itemName);
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            // Check if result set has data
            if (resultSet.next()) {
            	int category = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double cost = resultSet.getDouble(3);
                String description = resultSet.getString(5);
                String picture = resultSet.getString(4);
                
                // Create ItemObject instance
                item = new ItemObject(category, name, cost, picture, description);
                
                // We can fetch subItems and set them here if needed
                // item.setSubItems(fetchSubItems(itemName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return item;
    }
	
	public double cartTotal(ArrayList<ItemObject> cartItems) {
        double total = 0.0;
        
        // Checks first if cartItems is empty
        if (cartItems != null && !cartItems.isEmpty()) {
            // Not empty? Iterate over cart items
            for (ItemObject item : cartItems) {
                // Add cost of the main item
                total += item.getCost();
                
                // Check if item has subitems and the list is not empty
                ArrayList<SubItemObject> subItems = item.getSubItems();
                if (subItems != null && !subItems.isEmpty()) {
                    // Iterate over subitems and add their costs
                    for (SubItemObject subItem : subItems) {
                        total += subItem.getCost();
                    }
                }
            }
        }
        
        return total;
    }
	public ArrayList<SubItemObject> getFullSubItems(String name) {
        ArrayList<SubItemObject> subItems = new ArrayList<>();
        
        SubItemObject subItem;
        
        // Convert Category to int
        int catName = getSpecificSubCategory(name);
        System.out.println("Got Category: " + name + " which is #" + catName);
        
        try {
            // Connect to the database
            database = new KioskDbUtil(dataSource);
            connection = database.getConnection();
            
            // Prepare the SQL statement to get all items
            CallableStatement callableStatement = connection.prepareCall("{CALL getSubItemList(?)}");
            callableStatement.setInt(1, catName);
            
            // Execute the query
            resultSet = callableStatement.executeQuery();
            
            int count = 0;
            
            // Process the result set
            while (resultSet.next()) {
                
            	String subItemName = resultSet.getString(1);
                double cost = resultSet.getDouble(2);
                int category = resultSet.getInt(3);
                
                // Create ItemObject instance
                subItem = new SubItemObject(subItemName, cost, category);
                
                // Add item to the ArrayList
                subItems.add(subItem);
                
                count++;
                System.out.println("Items Retrieved: " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close JDBC objects
            database.closeConnection(connection, callableStatement, resultSet);
        }
        
        return subItems;
    }

}

