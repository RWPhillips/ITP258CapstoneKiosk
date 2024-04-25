package com.itp258capstonekiosk.services;

import javax.print.*;
import javax.print.attribute.*;
import javax.print.attribute.standard.*;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class PrintService {

	private DataSource dataSource;
	private Connection connection;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	private KioskDbUtil database;
	
	// Connection parameters
	public PrintService(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public void printReceipt(ArrayList<ItemObject> orderedItems, int ticketNumber) {
		// Create receipt content
		
		double totalCost = 0;
		StringBuilder receiptContent = new StringBuilder();
		receiptContent.append("Receipt          Ticket Number: ").append(ticketNumber).append("\n");
		receiptContent.append("Date: ").append(new Date()).append("\n");
		receiptContent.append("------------------------------\n");
		for (ItemObject item : orderedItems) {
			receiptContent.append(item.getName()).append("\t\t").append(item.getCost()).append("\n");
			
			totalCost += item.getCost();

			// Include subitems if available
			ArrayList<SubItemObject> subItems = item.getSubItems();
			if (subItems != null && !subItems.isEmpty()) {
				for (SubItemObject subItem : subItems) {
					receiptContent.append("\t").append(subItem.getName()).append("\t\t").append(subItem.getCost())
							.append("\n");
					
					totalCost += subItem.getCost();
				}
			}
		}
		receiptContent.append("------------------------------\n");
		receiptContent.append("Total Cost: ").append(totalCost).append("\n");

		// Print the receipt
		printText(receiptContent.toString());
	}

	public void printText(String text) {
		try {
			// Convert text to input stream
			InputStream inputStream = new ByteArrayInputStream(text.getBytes());

			// Set the document type
			DocFlavor myFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;

			// Create a Doc
			Doc myDoc = new SimpleDoc(inputStream, myFormat, null);

			// Build a set of attributes
			PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
			aset.add(new Copies(1));

			// Discover the printers that can print the format according to the instructions
			// in the attribute set
			javax.print.PrintService[] services = PrintServiceLookup.lookupPrintServices(myFormat, aset);

			// Create a print job from one of the print services
			if (services.length > 0) {
				DocPrintJob job = services[0].createPrintJob();
				job.print(myDoc, aset);
			} else {
				System.out.println("No printer found.");
			}
		} catch (PrintException e) {
			e.printStackTrace();
		}
	}

	public int generateOrderNumber() {
		int orderNumber = 0;
		try {
			
		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();
			
			String query = "SELECT orderID FROM orders WHERE id = 1";
			try (Statement statement = connection.createStatement()) {
				resultSet = statement.executeQuery(query);
				if (resultSet.next()) {
					orderNumber = resultSet.getInt("orderID") + 1;
					updateOrderNumber(orderNumber);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNumber;
	}

	public void updateOrderNumber(int orderNumber) {
		try {
			
		    // Connect to database
		    database = new KioskDbUtil(dataSource);
		    connection = database.getConnection();
			
			String query = "UPDATE orderID SET number = ? WHERE id = 1";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
				statement.setInt(1, orderNumber);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
