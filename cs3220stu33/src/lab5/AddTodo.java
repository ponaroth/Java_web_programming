package lab5;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab5/AddTodo")
public class AddTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// Get a reference to the guest book
		ArrayList<todoList> todoList = (ArrayList<todoList>) getServletContext().getAttribute("todoList");
		
		 //String taskText = (String) getServletContext().getAttribute("taskText");
		 String taskText = request.getParameter("taskText");
		
		 System.out.println(taskText);
		 
		
		todoList.add(new todoList(taskText, taskText));
		
		// Redirect the User back to the main page
		response.sendRedirect("todo");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
