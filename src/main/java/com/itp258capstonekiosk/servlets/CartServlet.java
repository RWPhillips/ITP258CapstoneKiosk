package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;
import com.itp258capstonekiosk.services.CartService;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get object
		HttpSession session = request.getSession();
		ItemObject item = (ItemObject) session.getAttribute("orderItem");
		
		CartService cart = new CartService(dataSource); 
		
		// Get strings
		String[] subItems = request.getParameterValues("subitems");
		

		if (subItems != null) {
			// Make sub-object array
			ArrayList<SubItemObject> subList = new ArrayList<SubItemObject>();
		
			// Go through array, grab each string, pass to db to get object, create object and store. 
			for (String i : subItems) {
				
				
				SubItemObject newSub = cart.getSubItem(i);
				subList.add(newSub);
			}
		
			// Add all sub items to the item
			item.setSubItems(subList);
		}
		ArrayList<ItemObject> items = (ArrayList<ItemObject>)session.getAttribute("cart");  
		if (items == null ) {
			items = new ArrayList<ItemObject>(); 
		}
		
		//if theres a value in the header get it
		String header = request.getHeader("hx-trigger-name");
		
		//add the item only if checkout is empty. otherwise this will readd if the page is refreshed. 
		if (!header.equalsIgnoreCase("checkout")) {
			items.add(item); 
		}
		// Store in the cart session header
		session.setAttribute("cart", items);
		
		

		
		System.out.println(header);
		
		//if the header is checkout, direct to the manage account page, otherwise, direct to the message about successfully adding an item. 
		if (header.equalsIgnoreCase("checkout")) {
			request.getRequestDispatcher("secure/manageCart.jsp").forward(request, response);
			}
		else request.getRequestDispatcher("secure/ItemAdded.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
