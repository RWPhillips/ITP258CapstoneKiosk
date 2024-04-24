package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.itp258capstonekiosk.services.AccountService;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Get data from form
		String username = request.getParameter("userName");
		String password = request.getParameter("createPass");
        String confirmPassword = request.getParameter("confirmCreate");
        String accountType = request.getParameter("accType");

        // Initialize int for account type
        int accType = 0;

        // Pick correct account
        if (accountType.equalsIgnoreCase("Administrator"))
        	accType = 3;
        else if (accountType.equalsIgnoreCase("Ticket Display"))
        	accType = 2;
        else
        	accType = 1;

        // Create account
        AccountService account = new AccountService(dataSource);
        account.createAccount(username, password, confirmPassword, accType);

        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/account.jsp");
 		dispatcher.forward(request, response);

		doGet(request, response);
	}

}