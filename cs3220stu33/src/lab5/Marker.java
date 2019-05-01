package lab5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab5/Marker")
public class Marker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id"));
		
		// Get a reference to the guest book
		ArrayList<todoList> todoList = (ArrayList<todoList>) getServletContext().getAttribute("todoList");
				
		// Remove the element that matches the specified ID
		for (todoList entry : todoList) {
			if (entry.getId() == id) {
				
				if (entry.getStatus() == true) {
					entry.status = false;
				} else {
					entry.status = true;
				}
				
				
				
				break;
			}
		}
		
		// Redirect the User back to the main page
		response.sendRedirect("todo");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
