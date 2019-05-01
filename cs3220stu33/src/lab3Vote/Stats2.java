package lab3Vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab3.MyGuestBookEntry;

@WebServlet("/lab3Vote/Stats2")
public class Stats2 extends HttpServlet {

	private static final long serialVersionUID = 1L;
	int count = 0;

	double pc1;
	double pc2;
	double pc3;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		int count1 = (int) getServletContext().getAttribute("count1");
		int count2 = (int) getServletContext().getAttribute("count2");
		int count3 = (int) getServletContext().getAttribute("count3");
		int totalCount = (int) getServletContext().getAttribute("totalCount");
		count++;

		if (count > 0) {
		pc1 = (count1 * 100) / totalCount;
		pc2 = (count2 * 100) / totalCount;
		pc3 = (count3 * 100) / totalCount;
		
		}

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("	<meta charset=\"UTF-8\">");

		out.println("<style>\n");

		out.println("img {\n" + "  width: auto;\n" + "  height: 100px;\n" + "padding: 5px" +"}");

		out.println("</style>");

		out.println("	<title>Statistic</title>");
		out.println(
				"	<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("	<h3>Voting Results</h3>");

		out.println("	<p> totally Votes is " + totalCount + "</p>");

		out.println("<form action=\"Stats1\" method=\"post\">");

		out.println("<table >");

		out.println("  <tr>");
		out.println("<th>");
		out.println("<img src=\"../lab3Assets/java.jpeg\" >");
		out.println("	 Java has been voted for " + count1 + " time(s). (" + pc1 + "%)");
		out.println("</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("<th>");
		out.println("<img src=\"../lab3Assets/python.jpeg\">");
		out.println("Python has been voted for " + count2 + " time(s). (" + pc2 + "%)");
		out.println("</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("<th>");
		out.println("<img src=\"../lab3Assets/C++.png\">");
		out.println("C++ has been voted for " + count3 + " time(s). (" + pc3 + "%)");
		out.println("</th>");
		out.println("  </tr>");

		out.println("</table>");

		out.println("	<input type=\"submit\" name=\"returnBtn\" value=\"Caste another vote\">");
		out.println("</form>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("returnBtn") != null) {
			response.sendRedirect("Vote");

		}
		doGet(request, response);

	}

}
