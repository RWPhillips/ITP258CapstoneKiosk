package com.itp258capstonekiosk.servlets;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class ImageService{

    public static void handleImageUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// Check if the request is multipart
        if (!ServletFileUpload.isMultipartContent(request)) {
            // Handle the case where the request is not multipart
            response.getWriter().println("Error: Request is not multipart");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("request not multipart");
        }

        // Get the part for the image file
        Part filePart = request.getPart("img");

        // Validate the part
        if (filePart == null || filePart.getSize() == 0) {
            // Handle the case where no file is uploaded
            response.getWriter().println("Error: No file uploaded");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("no file");

        }

        // Get the filename and content type
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String contentType = filePart.getContentType();

        //get the user's operating system
        String os = System.getProperty("os.name").toLowerCase();
        //get the location of the folder

        String location = System.getProperty("catalina.home");

        //check to see if its a windows os
        if (os.contains("win")) {
        	System.out.println("windows os");
        	//check to see if a folder exists

        	location = location + "\\orderUp\\images\\";

        	File folder = new File(location);
        	if (folder.exists()) {
        		System.out.println("folder");
        	}
        	else {
        		boolean status = folder.mkdirs();
        		System.out.println("made folders");
        	}
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
        	System.out.println("linux");

        	location = location + "/orderUp/images/";
        	File folder = new File(location);
        	if (folder.exists()) {
        		System.out.println("folder");
        	}
        	else {
        		boolean status = folder.mkdirs();
        		System.out.println("made folders");
        	}
        }


        //Save the file to a directory
        System.out.println(location);
        try (InputStream input = filePart.getInputStream();
             OutputStream output = Files.newOutputStream(Paths.get(location, fileName))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

    }
}
