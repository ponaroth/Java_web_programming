package Tournament;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Tournament/RankUp")
public class RankUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id"));
		
		// Get a reference to the guest book
		ArrayList<Team> list1 = (ArrayList<Team>) getServletContext().getAttribute("teamList");
				
		// Remove the element that matches the specified ID
		for (Team entry : list1) {
			if (entry.getId() == id) {
				
				entry.setRank(entry.rank + 1);;
				
				break;
			}
		}
		
		// Redirect the User back to the main page
		response.sendRedirect("bracket");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
