package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class CreateItemServlet
 */
@WebServlet("/CreateItemServlet")
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
		ItemService itemCat = new ItemService(dataSource);
		
		String name = request.getParameter("itemName");
		
		String cost = request.getParameter("itemCost");
		String desc = request.getParameter("itemDescription");
		
		System.out.println(name);

		//get the file part
		Part filePart = request.getPart("itemImage");

		//get the filename
		String filename = filePart.getSubmittedFileName();

		//call the service to store the image on the server.
		ImageService.handleImageUpload(request, response);

		//call the image service to add the image to the web server
		String url = request.getHeader("Host") + "/ITP258CapstoneKiosk/images/" + filename;
		System.out.println(url);


        // call the itemservice to create the category in the database.
		ItemService cat = new ItemService(dataSource);
		cat.createCategory(name, url);

        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/create-category.jsp");
 		dispatcher.forward(request, response);

		doGet(request, response);
	}

}