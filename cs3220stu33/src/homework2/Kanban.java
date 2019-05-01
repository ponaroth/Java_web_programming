package homework2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/homework2/kanban" }, loadOnStartup = 1)
public class Kanban extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<TaskEntry> list1 = new ArrayList<TaskEntry>();

		// Pre-populate the guest book with some entries

		list1.add(new TaskEntry("chore", "Loundry"));
		list1.add(new TaskEntry("grocery", "buy ketchup"));
		list1.add(new TaskEntry("Homework", "do homework"));
		list1.add(new TaskEntry("Before sleep", "apply for internships"));

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("list1", list1);

	}

	String nameText = "";
	String taskText = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<TaskEntry> list1 = (ArrayList<TaskEntry>) getServletContext().getAttribute("list1");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>To Do List</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");

		out.println("<style>");
		out.println(".column { border-width: medium; padding: 0 10px; width: 25%;}");
		out.println(".card { border: 3px solid black; padding: 10px; }");
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");

		
		out.println("<div class=\"container\">");
		

		out.println("<h1>Kan Ban</h1>");

		int size = list1.size();
		int doneCount = 0;
		String countMessage = "";

		for (TaskEntry entry : list1) {
			if (entry.getStatus() == true) {
				doneCount++;
			}
		}

		if (size <= 0) {

			countMessage = "no task at the moment";
		} else {
			// int leftCount = size - doneCount;
			// countMessage = leftCount + " of " + size + " remaining";
		}

		out.println("<div>" + countMessage + "</div>");

		out.println("<form action=\"AddTask\" method=\"post\">");
		out.println("<input type=\"text\" name=\"nameText\" placeholder=\"card title\"   >");
		out.println("<input type=\"text\" name=\"taskText\"  placeholder=\"Description\"  >");
		out.println("<input type=\"submit\" name=\"AddBtn\" value=\"Add A New Card\">");
		out.println("</form>");

		getServletContext().setAttribute("nameText", nameText);
		getServletContext().setAttribute("taskText", taskText);

		out.println("<hr>");

		int count = 1;

		out.println("<div class=\"row\">");

		out.println("<div class=\"column\">");
		out.println("<h2>To-Do</h2>");

		// print rank 1 entry
		for (TaskEntry entry : list1) {
			if (entry.getRank() == 1) {

				// out.println("<div class = \"row\">"); // card start
				out.println("<div class=\"card\">");

				out.println("    <h5 style=\"background-color: Tomato\" >" + entry.getName() + "</h5>");
				out.println("    <p>" + entry.getMarkedMessage() + "</p>");

				out.println(" <a href=\"MarkTask?id=" + entry.getId() + "\">Move to In-Progress</a> ");

				out.println(" <a href=\"DeleteTask?id=" + entry.getId() + "\">Delete</a>");
				out.println("  </div>"); // card stop

				count++;
			}
		}

		out.println("</div>");

		out.println("<div>");

		out.println("</div>"); // column1 end ////////////////////////////////////

		out.println("<div class=\"column\">"); // column2 start
		out.println("<h2>In-Progress</h2>");

		// print rank 2 entry
		for (TaskEntry entry : list1) {
			if (entry.getRank() == 2) {

				// out.println("<div class = \"row\">"); // card start
				out.println("<div class=\"card\">");

				out.println("    <h5 style=\"background-color: Orange\">" + entry.getName() + "</h5>");
				out.println("    <p>" + entry.getMarkedMessage() + "</p>");

				out.println(" <a href=\"MarkTask?id=" + entry.getId() + "\">Move to Finish</a> ");

				out.println(" <a href=\"DeleteTask?id=" + entry.getId() + "\">Delete</a>");
				out.println("  </div>"); // card stop

				count++;
			}
		}

		out.println("</div>"); // column2 end /////////////////////////////////

		out.println("<div class=\"column\">"); // column3 start
		out.println("<h2>Finished</h2>");

		// print rank 3 entry
		for (TaskEntry entry : list1) {
			if (entry.getRank() == 3) {

				// out.println("<div class = \"row\">"); // card start
				out.println("<div class=\"card\">");

				out.println("    <h5 style=\"background-color: Green\">" + entry.getName() + "</h5>");
				out.println("    <p>" + entry.getMarkedMessage() + "</p>");

				out.println(" <a href=\"DeleteTask?id=" + entry.getId() + "\">Delete</a>");
				out.println("  </div>"); // card stop

				count++;
			}
		}

		out.println("</div>"); // column3 end

		out.println("</div>");

//		out.println("<form action=\"kanban\" method=\"post\">");
//		out.println("<input type=\"submit\" name=\"clearBtn\" value=\"Clear All Tasks\">");
//		out.println("<form>");

		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TaskEntry> list1 = (ArrayList<TaskEntry>) getServletContext().getAttribute("list1");

		if (request.getParameter("AddBtn") != null) {

			System.out.println(nameText);
			response.sendRedirect("AddTask");

		}
		if (request.getParameter("clearBtn") != null) {
			list1.clear();

		}

		doGet(request, response);

	}

}
