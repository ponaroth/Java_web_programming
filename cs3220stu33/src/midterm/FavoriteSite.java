package midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/midterm/home" }, loadOnStartup = 1)
public class FavoriteSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<TaskEntry2> listA = new ArrayList<TaskEntry2>();

		// Pre-populate the guest book with some entries

		listA.add(new TaskEntry2("github.com", "online repository"));
		listA.add(new TaskEntry2("wikipedia.org", "Online Encyclopedia"));
		listA.add(new TaskEntry2("google.com", "Search engine"));
		listA.add(new TaskEntry2("calstatela.edu", "School website"));

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("listA", listA);

	}

	String nameText = "";
	String taskText = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<TaskEntry2> listA = (ArrayList<TaskEntry2>) getServletContext().getAttribute("listA");

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>My Favorite Sites</title>");
		
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");

		out.println("<style>");
		out.println(".column { border-width: medium; padding: 0 10px; width: 25%;}");
		out.println(".card { border: 3px solid black; padding: 10px; width:200%;}");
		out.println("#red { color:red }");
		out.println("#green { color:green }");
		out.println("#text-box { height:200%  }");
		
		
		
		out.println("</style>");

		out.println("</head>");
		out.println("<body>");

		
		out.println("<div class=\"container\">");
		

		out.println("<h1>My Favorite Websites</h1>");

		int size = listA.size();
		int doneCount = 0;
		String countMessage = "";

		for (TaskEntry2 entry : listA) {
			if (entry.getStatus() == true) {
				doneCount++;
			}
		}

		if (size <= 0) {

			countMessage = "nothing at the moment";
		} else {
			// int leftCount = size - doneCount;
			// countMessage = leftCount + " of " + size + " remaining";
		}

		out.println("<div>" + countMessage + "</div>");

		out.println("<form action=\"AddTask2\" method=\"post\">");
		out.println("<input type=\"text\" name=\"nameText\" placeholder=\"card title\"   >");
		out.println("<input id=\"text-box\" type=\"text\" name=\"taskText\"  placeholder=\"Description\"  >");
		out.println("<input type=\"submit\" name=\"AddBtn\" value=\"Add A New Site\">");
		out.println("</form>");

		getServletContext().setAttribute("nameText", nameText);
		getServletContext().setAttribute("taskText", taskText);

		out.println(" <a id=\"green\" href=\"SortByLikes\">Sort by Likes</a> ");
		out.println("<a> | </a>");
		out.println(" <a id=\"green\" href=\"SortByDislikes\">Sort by Dislikes</a> ");
		out.println("<hr>");

		int count = 1;

		out.println("<div class=\"row\">");

		out.println("<div class=\"column\">");

		// print rank 1 entry
		for (TaskEntry2 entry : listA) {
			

				// out.println("<div class = \"row\">"); // card start
				out.println("<div class=\"card\">");

				out.println("    <a target=\"_blank\"" + "href=\"http://" + entry.getName() + "\">" + entry.getName() + "</a>");
				out.println("    <p>" + entry.getMarkedMessage() + "</p>");

				out.println(" <a href=\"Like?id=" + entry.getId() + "\">Like (" + entry.getLike() + ")</a> ");
				out.println(" <a id= \"red\" href=\"Dislike?id=" + entry.getId() + "\">Dislike (" + entry.getDislike() + ")</a> ");

				out.println(" <a href=\"DeleteTask2?id=" + entry.getId() + "\">Delete</a>");
				
				out.println("  </div>"); // card end

				count++;
			
		}
		out.println("</div>");

		out.println("</div>");

		out.println("<div>");
		

		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<TaskEntry2> listA = (ArrayList<TaskEntry2>) getServletContext().getAttribute("listA");

		if (request.getParameter("AddBtn") != null) {

			System.out.println(nameText);
			response.sendRedirect("AddTask2");

		}
		if (request.getParameter("clearBtn") != null) {
			listA.clear();

		}

		doGet(request, response);

	}

}
