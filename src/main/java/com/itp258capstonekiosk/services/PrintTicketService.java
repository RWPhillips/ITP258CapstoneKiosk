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

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.io.IOUtils;

public class PrintTicketService {

	private DataSource dataSource;
	private Connection connection;
	private CallableStatement callableStatement;
	private ResultSet resultSet;
	private KioskDbUtil database;

	// Connection parameters
	public PrintTicketService(DataSource theDataSource) {
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
            
            // Create a new PDF document
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            
            // Create a new content stream to add content to the PDF
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType1Font font = new PDType1Font(Standard14Fonts.FontName.COURIER);
            
            // Set font and font size
            contentStream.setFont(font, 12);

            // Split text into lines
            List<String> lines = readTextFile(text);

            // Add each line to the content stream
            float y = page.getMediaBox().getHeight() - 72; // Start at the top of the page
            for (String line : lines) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 14);
                contentStream.newLineAtOffset(72, y); // Start at the left margin
                contentStream.showText(line);
                contentStream.endText();
                y -= 14; // Move to the next line (assuming font size of 12 and leading of 2)
            }

            // Close the content stream
            contentStream.close();

            // Save the PDF document
            document.save("C:/Users/Robert/Desktop/output.pdf");
            document.close();

            System.out.println("PDF file created successfully.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
    public static List<String> readTextFile(String text) {
        List<String> lines = new ArrayList<>();
        String[] splitLines = text.split("\n");
        for (String line : splitLines) {
        	line = line.replaceAll("\n", " ");
        	line = line.replaceAll("\t", " ");
            lines.add(line);
        }
        return lines;
    }

	public int generateOrderNumber() {
		int orderNumber = 0;
		try {

			// Connect to database
			database = new KioskDbUtil(dataSource);
			connection = database.getConnection();

			String query = "SELECT orderID FROM orders WHERE orderID = 1";
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
