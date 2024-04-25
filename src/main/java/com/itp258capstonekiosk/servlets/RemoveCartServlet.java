package com.itp258capstonekiosk.servlets;

import java.io.IOException;
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
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/RemoveCartServlet")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get the array of parent items for deleting
		String[] parents = request.getParameterValues("parentItem");
		String[] children = request.getParameterValues("subItems");
		String[] parentTrap = request.getParameterValues("parentID");
		
		// Retrieve the cart from session
		HttpSession session = request.getSession();
		
		// Get array of cart items
		ArrayList<ItemObject> cartItems = (ArrayList<ItemObject>) session.getAttribute("cart");

		// If there are cart items..
		if (cartItems != null) {
			//if there are full items that need updated
			if (parents != null) {
				// grab each item from the parent array
				for (String parent : parents) {

					// Loop through and remove the item
					for (int i = 0; i < cartItems.size(); i++) {
						System.out.println("item " + i + "id: " + cartItems.get(i).getId());
						if (cartItems.get(i).getId() == Integer.parseInt(parent)) {
							cartItems.remove(i);
						}
					}
				}
			}
			//if there are sub items that need removed
			if (children != null) {
				System.out.println("inside the subcheck"); 
				//for each child, get the corresponding parent id
				for (int i=0; i< children.length; i++) {
					
					ArrayList<SubItemObject> subObj = new ArrayList<>(); 
					int childID = Integer.parseInt(children[i]); 
					int parentID = Integer.parseInt(parentTrap[i]);
					
					System.out.println("ChildID: " + childID + " ParentID: " + parentID);
					//iterate over the cart items. 
					for (int j=0; j < cartItems.size(); j++) {
						//if the item has the same id as the parent item
						System.out.println("ITEMID:" + cartItems.get(j).getId());
						if (cartItems.get(j).getId() == parentID) {
							
							//make sure the item has subitems
							if (cartItems.get(j).getSubItems() != null) {
							//iterate over the arraylist in the item
								for (SubItemObject obj : cartItems.get(j).getSubItems()) {
									System.out.println("SubitemID:" + obj.getId()); 
									//if the subitem matches in the one that needs deleted
									if (obj.getId() == childID) {
										//delete it. 
										subObj.add(obj); 
									}
								}
							cartItems.get(j).getSubItems().removeAll(subObj);
							}
						}
					}
				}
			}

		    // Update the session with the modified ArrayList
		    session.setAttribute("cart", cartItems);
		}

		
		//return the account management page
		request.getRequestDispatcher("secure/ItemAdded.jsp").forward(request, response);
}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
