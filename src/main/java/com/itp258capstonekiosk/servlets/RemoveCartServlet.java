package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itp258capstonekiosk.objects.ItemObject;

/**
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/RemoveCartServlet")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Retrieve the cart from session
		HttpSession session = request.getSession();
		
		// Get array of cart items
		ArrayList<ItemObject> cartItems = (ArrayList<ItemObject>) session.getAttribute("cart");

		// If there are cart items..
		if (cartItems != null) {
			
		    // Get the itemId (based on the index of the item in the cart)
		    int itemIdToRemove = Integer.parseInt(request.getParameter("itemId"));

		    // Loop through and remove the item
		    for (int i = 0; i < cartItems.size(); i++) {
		    	
		    	if (i == itemIdToRemove) {
		    		cartItems.remove(i);
		    	}
		    }

		    // Update the session with the modified ArrayList
		    session.setAttribute("cart", cartItems);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
