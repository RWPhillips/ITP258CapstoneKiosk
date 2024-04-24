package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;

/**
 * Servlet implementation class DisplayCartServlet
 */
@WebServlet("/DisplayCartServlet")
public class DisplayCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	NumberFormat nf = NumberFormat.getCurrencyInstance(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//start the session
		HttpSession session = request.getSession();
		
		//fetch the arraylist from the session
		ArrayList<ItemObject> cart = (ArrayList<ItemObject>) session.getAttribute("cart");
		
		//System.out.println("Cart Contents:");
		
		double total = 0; 
		//iterate over the array
		for (ItemObject obj : cart ) {
			//add the cost of the item to the total
			total += obj.getCost();
			System.out.println("Obj Cost is: " + obj.getCost());
			
			
			
			//if the item has a sub items array, iterate over it
			if (obj.getSubItems() != null) {
				for (SubItemObject sub : obj.getSubItems()) {
					total += sub.getCost();
					System.out.println("Sub obj cost: " + sub.getCost());
				}
			}
		}
		
		System.out.println("Total at end: " + total); 
		
		request.setAttribute("cartTotal", nf.format(total)); 
		
		request.getRequestDispatcher("secure/updatedcart.jsp").forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
