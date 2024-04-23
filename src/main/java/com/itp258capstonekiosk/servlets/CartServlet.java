package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		ItemObject item = (ItemObject) session.getAttribute("orderItems");
		
		// Get strings
		String[] subItems = request.getParameterValues("subitems");
		System.out.println(subItems);
		
		// Make sub-object array
		ArrayList<SubItemObject> subList = new ArrayList<SubItemObject>();
		
		// Go through each string, create a new sub object with the string, and then store it into sub-object array
		for (String i : subItems) {
			SubItemObject newSub = new SubItemObject(i);
			subList.add(newSub);
		}
		
		// Add all sub items to the item
		item.setSubItems(subList);
		
		// Store in the session header
		session.setAttribute("itemObject", item);
		
        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/order.jsp");
 		dispatcher.forward(request, response);

		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
