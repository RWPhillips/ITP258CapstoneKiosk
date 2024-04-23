package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class ItemDetailsServlet
 */
@WebServlet("/ItemDetailsServlet")
public class ItemDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getHeader("hx-trigger-name");
		
		//create an http session
		HttpSession session = request.getSession(); 
		
		
		System.out.println(name); 
		
		ItemService itemService = new ItemService(dataSource); 
		
		//ItemObject item = new ItemObject(16, "Al Pastor Tacos", 12.99, "/images/Tacos.jpg", "Yes, Tacos are a sandwich! Four Al pastor tacos served with onions and cilantro on house made corn tortillas."); 
		
		ItemObject item = itemService.getCompleteItem(name); 
		
		//add the current item being worked with to the http session
		session.setAttribute("orderItem",item);
		
		//System.out.println("cat: " + item.getCategory() + " name: " + item.getName() + " cost: " + item.getCost() + " url: " + item.getPicture() + " desc: " + item.getDescription());
		
	
		
		String	html = 
				"<div class=\"item\"><div class=\"imgBox\"><img src=\"" + item.getPicture() + "\" class=\"picture\"/></div><div class=\"stats\"><p class=\"desc\">" + item.getDescription() +"<p class=\"cost\">$"+ item.getCost() +  "</p>" +
				"<div class=\"buttonContainer\"><button class=\"customize\" name=\"" + item.getName() +
				"\"  hx-get=\"/ITP258CapstoneKiosk/CustomizeItemServlet\" hx-target=\".content\">Customize</button><button class=\"addCart\" name=\"" + item.getName() 
				+ "\"  hx-get=\"/ITP258CapstoneKiosk/CartServlet\" hx-target=\".content\">Add to Cart</button></div></div></div>";
		
		System.out.println(html); 
	
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
