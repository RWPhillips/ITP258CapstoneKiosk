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

import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class DeleteSubCategoryServlet
 */
@WebServlet("/DeleteSubCategoryServlet")
public class DeleteSubCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSubCategoryServlet() {
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
		
		String subcat = request.getParameter("delSubcategory");
		System.out.println(subcat); 
		
		ItemService item = new ItemService(dataSource); 
		
        // Send to JSP page
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/secure/create-subitem-category.jsp");
 		dispatcher.forward(request, response);
		
		item.deleteSubcategory(subcat);
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
