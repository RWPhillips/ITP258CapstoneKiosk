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

/**
 * Servlet implementation class GetAccountServlet
 */
@WebServlet("/GetAccountServlet")
public class GetAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Get user name from the database
		String username = request.getParameter("userName");

		// Get the account service
        AccountService account = new AccountService(dataSource);
        
        // Get status when deleting account (this no longer works, as we are not returning anything)
        ArrayList<AccountObject> accountList = account.getAccounts(username);

	    // Get ready to generate options with StringBuilder
	    StringBuilder options = new StringBuilder();
	    
	    // Go through each account in the array
	    for (AccountObject acc : accountList) {
	    	
	    	// Build the options string
	        options.append("<option value=\"").append(acc.getUsername()).append("\">").append(acc.getUsername()).append("</option>");
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
