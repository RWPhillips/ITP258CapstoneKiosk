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

import com.itp258capstonekiosk.objects.CategoryObject;
import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class DisplayCategoriesServlet
 */
@WebServlet("/DisplayCategoriesServlet")
public class DisplayCategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//create an item service
		ItemService item = new ItemService(dataSource); 
		
		//get the arraylist of categories
		ArrayList<CategoryObject> categories = item.getFullCategory();
		
		String html = ""; 
		
		for (CategoryObject category: categories) {
			System.out.println("url: " + category.getImageURL() + " name " + category.getName()); 
			html = html + "<a href src=\"\" class=\"img\" name=\"" + category.getName() + "\" hx-trigger=\"click\" hx-get=\"${pageContext.request.contextPath}/secure/DisplayItemsServlet\">"+
			"<div class=\"catContainer\"><img src=\"" + category.getImageURL() + "\" class=\"catImg\"\"><p class=\"catText\">" + category.getName() + "</p></div></a>"; 
			System.out.println(html); 
		}
		
	    // Set content type
	    response.setContentType("text/html");

	    // Write the options to the response
	    response.getWriter().write(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
