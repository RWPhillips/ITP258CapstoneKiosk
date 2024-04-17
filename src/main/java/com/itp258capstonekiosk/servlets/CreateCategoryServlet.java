package com.itp258capstonekiosk.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;


import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class CreateCategoryServlet
 */
@WebServlet("/CreateCategoryServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class CreateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCategoryServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get data from form
				String name = request.getPart("addCategory").toString(); 
				String url = request.getPart("img").toString(); 
				Part filePart = request.getPart("img");
				
				System.out.println(url);
				System.out.println(name); 
		        
		        // Initialize int for account type
				ItemService cat = new ItemService(dataSource);
				//cat.createCategory(name, url);
				
				//get the servlet context, pass it into the image service
				 ServletContext context = getServletContext();
				
				ImageService.handleImageUpload(request, response, context);

		        // Send to JSP page
		 		//RequestDispatcher dispatcher = request.getRequestDispatcher("/public/index.jsp");
		 		//dispatcher.forward(request, response);
		 		


				doGet(request, response);
	}

}
