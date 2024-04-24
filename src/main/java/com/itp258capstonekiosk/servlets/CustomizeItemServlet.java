package com.itp258capstonekiosk.servlets;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.itp258capstonekiosk.objects.ItemObject;
import com.itp258capstonekiosk.objects.SubItemObject;
import com.itp258capstonekiosk.services.ItemService;

/**
 * Servlet implementation class CustomizeItemServlet
 */
@WebServlet("/CustomizeItemServlet")
public class CustomizeItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/kioskdatabase")
	private DataSource dataSource;
	
	NumberFormat nf = NumberFormat.getCurrencyInstance(); 
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomizeItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//create an item service
		ItemService item = new ItemService(dataSource); 
		
		//get the triggering button name from the header
		String name = request.getHeader("hx-trigger-name");
		
		//debug text
		System.out.println("trigger name: " + name);
		
		//get all the subcategories in an arraylist
		ArrayList<String> subcat = item.getSubCategories();
		
		//string to hold the html
		String html = "<form class=\"customizeForm\" hx-get=\"/ITP258CapstoneKiosk/CartServlet\" hx-target=\".content\" name=\"form\">"; 
		
		//loop over the array list constructing the html
		for (String catName : subcat) {
			html = html + "<div class=\"subParent\"><h3 class=\"subHead\">" + catName + "</h3><div class=\"subContainer\">" ; 
			
			ArrayList<SubItemObject> sub  = item.getFullSubItems(catName);
			for (SubItemObject object : sub) {
				html = html + "<div class=\"checkLabels\"><input type=\"checkbox\" class=\"hidden\" id=\"" 
						+ object.getName()  +"\" name=\"subitems\" value=\"" + object.getName() + "\"><label class=\"subLabel\" for=\"" + object.getName() + "\">" + object.getName() + " " + nf.format(object.getCost())  + "</label></div>";  
			}
			html = html + "</div></div>";
		}

		
		//add the checkout button. 
		html = html + "</div><div class=\"cartButton\"><button class=\"customCart\" name=\"" + name + "\" >Add to Cart</button></div></div>";
		//close the form
		html = html + "</form>";
		
	    // Set content type
	    response.setContentType("text/html");

	    // Write the options to the response
	    response.getWriter().write(html.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
