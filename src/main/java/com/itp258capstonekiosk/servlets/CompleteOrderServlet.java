package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.util.ArrayList;

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
import com.itp258capstonekiosk.services.PrintService;

/**
 * Servlet implementation class CompleteOrderServlet
 */
@WebServlet("/CompleteOrderServlet")
public class CompleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompleteOrderServlet() {
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
		
		// Get the Cart
		HttpSession session = request.getSession(false);
		
		// Get array of cart items
		ArrayList<ItemObject> cartItems = (ArrayList<ItemObject>) session.getAttribute("cart");
		
		// Data
		PrintService print = new PrintService(dataSource);
		
		// Generate Ticket Number
		int ticketNum = print.generateOrderNumber();
		
		// Print Receipt
		print.printReceipt(cartItems, ticketNum);
		

		// Empty the Cart. 
		if (session != null) {
			 session.setAttribute("cart", null);
		}

        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/splash-screen.jsp");
 		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
