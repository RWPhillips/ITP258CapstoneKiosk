package com.itp258capstonekiosk.servlets;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageService{
	
    public static void handleImageUpload(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
    	
    	// Check if the request is multipart
        if (!ServletFileUpload.isMultipartContent(request)) {
            // Handle the case where the request is not multipart
            response.getWriter().println("Error: Request is not multipart");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("request not multipart");
            return;
        }

        // Get the part for the image file
        Part filePart = request.getPart("img");

        // Validate the part
        if (filePart == null || filePart.getSize() == 0) {
            // Handle the case where no file is uploaded
            response.getWriter().println("Error: No file uploaded");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("no file");
            return;
        }

        // Get the filename and content type
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String contentType = filePart.getContentType();
        
        

        // Save the file to a directory
        String uploadDirectory = servletContext.getRealPath("/src/main/web/images"); // Assumes "uploads" directory exists
        System.out.println(uploadDirectory); 
        try (InputStream input = filePart.getInputStream();
             OutputStream output = Files.newOutputStream(Paths.get(uploadDirectory, fileName))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }

        // Respond with success message
        response.getWriter().println("File uploaded successfully");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
