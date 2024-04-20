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

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;
import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class GetSubItemServlet
 */
@WebServlet("/GetSubItemServlet")
public class GetSubItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSubItemServlet() {
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
		System.out.println("Getting Sub Items...");
		
		ItemService subItem = new ItemService(dataSource);
		
		String subCat = request.getParameter("selectCat");
		System.out.println("Sub Category Pulled: " + subCat);
		
        // Get items from certain category only
        ArrayList<SubItemObject> subItemList = subItem.getSubItemFromCategory(subCat);

	    // Get ready to generate options with StringBuilder
	    StringBuilder options = new StringBuilder();

	    // Go through each account in the array
	    for (SubItemObject i : subItemList) {

	    	// Build the options string
	        options.append("<option value=\"").append(i.getName()).append("\">").append(i.getName()).append("</option>");
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
