package homework2;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/homework2/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// Get a reference to the guest book
		ArrayList<TaskEntry> list1 = (ArrayList<TaskEntry>) getServletContext().getAttribute("list1");
		
		 //String taskText = (String) getServletContext().getAttribute("taskText");
		String nameText = request.getParameter("nameText");
		 String taskText = request.getParameter("taskText");
		 
		
		 
		
		list1.add(new TaskEntry(nameText, taskText));
		
		// Redirect the User back to the main page
		response.sendRedirect("kanban");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
