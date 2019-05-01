package midterm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id"));
		
		// Get a reference to the guest book
		ArrayList<TaskEntry2> list1 = (ArrayList<TaskEntry2>) getServletContext().getAttribute("listA");
				
		// Remove the element that matches the specified ID
		for (TaskEntry2 entry : list1) {
			if (entry.getId() == id) {
				
				entry.upLike();
				
				break;
			}
		}
		
		// Redirect the User back to the main page
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
