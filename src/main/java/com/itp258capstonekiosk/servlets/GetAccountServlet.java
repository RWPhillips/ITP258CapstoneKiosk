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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String username = request.getParameter("userName");

        AccountService account = new AccountService(dataSource);
        
        // Get status when deleting account
        ArrayList<AccountObject> accountList = account.getAccounts(username);
        
        // Store session w/ status
        HttpSession session = request.getSession(true);
        session.setAttribute("accounts", accountList); 

        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/public/index.jsp");
 		dispatcher.forward(request, response);
	}

}
