package midterm;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/AddTask2")
public class AddTask2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		ArrayList<TaskEntry2> list1 = (ArrayList<TaskEntry2>) getServletContext().getAttribute("listA");
		
		
		String nameText = request.getParameter("nameText");
		 String taskText = request.getParameter("taskText");
		 
		
		 if (nameText == "" || nameText == null) {
			 
		 } else {
		
		list1.add(new TaskEntry2(nameText, taskText));
		
		 }
		
		// Redirect the User back to the main page
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
