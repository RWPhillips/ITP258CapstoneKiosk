package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.AccountObject;
import com.itp258capstonekiosk.services.AccountService;
import com.itp258capstonekiosk.services.ItemService;

@WebServlet("/GetCategoryServlet")
public class GetCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCategoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("inside getCat");
		

		ItemService itemService = new ItemService(dataSource);
		
        //Get the categories
        ArrayList<String> categories = itemService.getCategories();
        
	    // Get ready to generate options with StringBuilder
	    StringBuilder options = new StringBuilder();
	    
	    // Go through each value in the arraylist
	    for (String string : categories) {
	    	
	    	// Build the options string
	        options.append("<option value=\"").append(string).append("\">").append(string).append("</option>");
	    }

	    // Set content type
	    response.setContentType("text/html");
	    
	    // Write the options to the response
	    response.getWriter().write(options.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
