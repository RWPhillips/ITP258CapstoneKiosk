package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.itp258capstonekiosk.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LoginService login;
    private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public LoginServlet(DataSource theDataSource) {
    	this.dataSource = theDataSource;
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
	    login = new LoginService(dataSource);

	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    // Validate the user name and hashed password against the database
	    boolean validLogin = login.validateUser(username, password);

	    if (validLogin) {
	        // Create a session for the user
	        HttpSession session = request.getSession(true);
	        
	        // Store user information in the session
	        session.setAttribute("username", username); 
	        session.setAttribute("Role", "user");
	        session.setAttribute("authenticated", true);

            // Generate a secure token
            String token = login.generateToken(username);

            // Set a cookie with the token
            Cookie rememberMeCookie = new Cookie("rememberMe", token);
            rememberMeCookie.setMaxAge(30 * 24 * 60 * 60); // 30 days
            rememberMeCookie.setSecure(true);
            rememberMeCookie.setHttpOnly(true);
            response.addCookie(rememberMeCookie);

	        // Redirect the user to the main page
	        System.out.println("Your login is valid!");
	        
	        // Send to JSP page
	 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/admin.jsp");
	 		dispatcher.forward(request, response);
	        
	    } else {
	    	
	    	// Login error
	    	System.out.println("Your login is NOT valid!");
	 		RequestDispatcher dispatcher = request.getRequestDispatcher("/public/index.jsp?error=1");
	 		dispatcher.forward(request, response);
	    }
	}

}
