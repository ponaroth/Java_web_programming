package lab5;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/lab5/todo" }, loadOnStartup = 1)
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<todoList> todoList = new ArrayList<todoList>();

		// Pre-populate the guest book with some entries

		todoList.add(new todoList("1", "Loundry"));
		todoList.add(new todoList("2", "buy ketchup"));
		todoList.add(new todoList("3", "do homework"));
		todoList.add(new todoList("4", "apply for internships"));
		

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("todoList", todoList);

	}
	
	String taskText = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<todoList> todoList = (ArrayList<todoList>) getServletContext().getAttribute("todoList");


		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>To Do List</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"container\">");

		out.println("<h1>To Do</h1>");
		
		int size = todoList.size();
		int doneCount = 0;
		String countMessage = "";
		
		for (todoList entry : todoList) {
			if (entry.getStatus() == true) {
				doneCount++;
			}
		} 
		
		if (size <= 0) {
			
			countMessage = "no task at the moment";
		} else {
			int leftCount = size - doneCount;
			countMessage = leftCount + " of " + size + " remaining";
		}
		
		out.println("<div>" + countMessage +  "</div>");
		
		

		out.println("<form action=\"AddTodo\" method=\"post\">");
		out.println("<input type=\"text\" name=\"taskText\">");
		out.println("<input type=\"submit\" name=\"AddBtn\" value=\"Add Todo\">");
		out.println("</form>");
		
		getServletContext().setAttribute("taskText", taskText);
		
		

		out.println("<hr>");

		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println("  <tr>");
		out.println("    <th>ID</th>");
		out.println("    <th>Tasks</th>");
		out.println("    <th>Actions</th>");
		out.println("  </tr>");

		int count = 1;

		// print search results in a table
		for (todoList entry : todoList) {
			out.println("  <tr>");
			out.println("    <td>" + entry.getId() + "</td>");
			out.println("    <td>" + entry.getMarkedMessage() + "</td>");
			
			out.println("    <td>");
			
			
			if (entry.getStatus() == false) {
				out.println(" <a href=\"Marker?id=" + entry.getId() + "\">Mark as Done</a> ");
			} else {
				out.println(" <a href=\"Marker?id=" + entry.getId() + "\">Mark as Un-Done</a> ");
			}
			
			
			out.println(" | ");
			out.println(" <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete</a>");
			out.println("    </td>");

			out.println("  </tr>");

			count++;

		}

		out.println("</table>");


		out.println("</div>");
		
		out.println("<div>");
		
		out.println("<form action=\"todo\" method=\"post\">");
		
		out.println("<input type=\"submit\" name=\"clearBtn\" value=\"Clear All Tasks\">");
		out.println("<form>");
		
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<todoList> todoList = (ArrayList<todoList>) getServletContext().getAttribute("todoList");
		
		if (request.getParameter("AddBtn") != null) {
			
			System.out.println(taskText);
			response.sendRedirect("AddTodo");
			
		}
		if (request.getParameter("clearBtn") != null) {
			todoList.clear();
			
			
		}
		
		
		
		doGet(request, response);

	}

}
