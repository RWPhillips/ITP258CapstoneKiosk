package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class CreateItemServlet
 */
@WebServlet("/CreateItemServlet")
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
public class CreateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemService item = new ItemService(dataSource);
		
		String catName = request.getParameter("createItem");
		int catId = item.getSpecificCategory(catName);
		
		String name = request.getParameter("itemName");
		
		String cost = request.getParameter("itemCost");
		double costNum = Double.parseDouble(cost);
		
		String desc = request.getParameter("itemDescription");
		
		System.out.println(name);

		//get the file part
		Part filePart = request.getPart("img");

		//get the filename
		String filename = filePart.getSubmittedFileName();

		//call the service to store the image on the server.
		ImageService.handleImageUpload(request, response);

		//call the image service to add the image to the web server
		String url =" /images/" + filename;
		System.out.println(url);

		// Create item
		item.createItem(catId, name, costNum, url, desc);

        // call the itemservice to create the category in the database.
		//item.createCategory(name, url);

        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/create-item.jsp");
 		dispatcher.forward(request, response);

		doGet(request, response);
	}

}
