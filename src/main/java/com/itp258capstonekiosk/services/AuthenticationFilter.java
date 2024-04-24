package com.itp258capstonekiosk.services;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthenticationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		    HttpServletResponse httpResponse = (HttpServletResponse) response;

		    HttpSession session = httpRequest.getSession(false); // Don't create a new session

		    // Check if the user is logged in (you can use any authentication attribute)
		    if (session == null || session.getAttribute("username") == null) {
		        // User is not logged in
		        String requestURI = httpRequest.getRequestURI();
		        if (!requestURI.endsWith("/public/logout.jsp") &&
		            !requestURI.endsWith("/public/index.jsp")) {
		            // Redirect to the login page only if the request is not for a public page
		            httpResponse.sendRedirect(httpRequest.getContextPath() + "/public/index.jsp");
		            System.out.println("Your session is not valid!");
		            return; // Stop filter chain processing
		        }
		    }

		    if (session != null && httpRequest.getRequestURI().endsWith("/secure/account.jsp") && session.getAttribute("username") != null) {
		    	
			    // User is logged in or accessing a public page; allow the request to proceed
			    System.out.println("Your session is valid, says the filter!");
			    
		        // If the user is on login.jsp, redirect to /secure/news.jsp
			    if (session.getAttribute("Role") == "admin")
			    	httpResponse.sendRedirect(httpRequest.getContextPath() + "/secure/account.jsp");
			    else
			    	httpResponse.sendRedirect(httpRequest.getContextPath() + "/secure/splash-screen.jsp");
			    
		        chain.doFilter(request, response);
		        
		    } else {
		        // Otherwise, allow the request to proceed
		        chain.doFilter(request, response);
		    }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
