package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.services.ItemService;


/**
 * Servlet implementation class DisplayItemsServlet
 */
@WebServlet("/DisplayItemsServlet")
public class DisplayItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayItemsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		
		//get the category name out of the header. 
		String category = request.getHeader("hx-trigger-name");

		
		ItemService item = new ItemService(dataSource);
		ArrayList<ItemObject> items = item.getItemFromCategory(category); 

		String html = "<h2 class=\"contentH2\">" + category + "</h2><div class=\"contentContainer\">" ; 
		
		for (ItemObject catitem : items) {
			html = html + "<button class=\"catContainer\" name=\"" + catitem.getName() + "\"  hx-get=\"/ITP258CapstoneKiosk/ItemDetailsServlet\" hx-target=\".content\" >" +
			"<img src=\"" + catitem.getPicture() + "\" class=\"catImg\"><p class=\"catText\">" +  catitem.getName() + "</p></button>"; 
			
						System.out.println(html); 
		}
		
		html = html + "</div>";
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
