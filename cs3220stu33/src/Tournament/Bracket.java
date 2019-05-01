package Tournament;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Tournament/bracket" }, loadOnStartup = 1)
public class Bracket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<Team> teamList = new ArrayList<Team>();

		// Pre-populate the guest book with some entries

		teamList.add(new Team("1", "Kansas"));
		teamList.add(new Team("2", "Kentucky"));
		teamList.add(new Team("3", "Loyola"));
		teamList.add(new Team("4", "Nevada"));
		teamList.add(new Team("5", "villanova"));
		teamList.add(new Team("6", "West Virginia"));
		teamList.add(new Team("7", "Texas"));
		teamList.add(new Team("8", "Perdue"));
		

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("teamList", teamList);

	}
	
	String taskText = "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Team> teamList = (ArrayList<Team>) getServletContext().getAttribute("teamList");


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
		
		int size = teamList.size();
		int doneCount = 0;
		String countMessage = "";
		
		for (Team entry : teamList) {
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
		
		getServletContext().setAttribute("taskText", taskText);
		
		

		out.println("<hr>");

		out.println("<table class=\"table table-bordered table-striped table-hover\">");

		int count = 1;

		// print search results in a table
		for (Team entry : teamList) {
			out.println("  <tr>");
			
			out.println("    <td>" + entry.getMessage() + "</td>");
			
			out.println("    <td>");
			
			
		
			out.println(" <a href=\"RankUp?id=" + entry.getId() + "\">Mark as Done</a> ");
			
			
			
			out.println(" | ");
//			out.println(" <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete</a>");
			out.println("    </td>");

			out.println("  </tr>");

			count++;

		}

		out.println("</table>");


		out.println("</div>");
		
		out.println("<div>");
		
		
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Team> teamList = (ArrayList<Team>) getServletContext().getAttribute("teamList");
		
		
		doGet(request, response);

	}

}
