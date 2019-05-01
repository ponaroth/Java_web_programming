package lab3Vote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab3.MyGuestBookEntry;

@WebServlet(urlPatterns = { "/lab3Vote/Vote" }, loadOnStartup = 1)
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int count1 = 0;
	int count2 = 0;
	int count3 = 0;
	int totalCount = 0;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("	<meta charset=\"UTF-8\">");
		out.println("<link rel='stylesheet' href='.css' type='text/css' media='all'/>");
		
		out.println("	<title>Lab3 Vote</title>");
		out.println(
				"	<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");

		out.println("<div class=\"container\">");
		out.println("	<h1>Vote for your favorite thing</h1>");

		out.println("<div class=\"container\">");

		/////////////////// selection manu ////////
		//style
		out.println("<style>\n" + 
				"div.gallery {\n" + 
				"  margin: 5px;\n" + 
				"  border: 1px solid #ccc;\n" + 
				"  float: left;\n" + 
				"  width: 180px;\n" + 
				"}");
		out.println("div.gallery:hover {\n" + 
				"  border: 1px solid #777;\n" + 
				"}");
		out.println("div.gallery img {\n" + 
				"  width: auto;\n" + 
				"  height: 100px;\n" + 
				"}");
		out.println("div.desc {\n" + 
				"  padding: 15px;\n" + 
				"  text-align: center;\n" + 
				"}");
		out.println("</style>");
		
		out.println("");
		
		/////////////
		

		// images
		out.println("<div class=\"gallery\">");
		out.println("<img src=\"../lab3Assets/java.jpeg\"  width=\"200\" height=\"200\">");
		out.println("<div class=\"desc\">");
		out.println("<form action=\"Vote\" method=\"post\">");
		out.println("	<input type=\"submit\" name=\"vote1Btn\" value=\"Vote for 1\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
	
		// choice 2
		out.println("<div class=\"gallery\">");
		out.println("<img src=\"../lab3Assets/python.jpeg\"  width=\"200\" height=\"200\">");
		out.println("<div class=\"desc\">");
		out.println("<form action=\"Vote\" method=\"post\">");
		out.println("	<input type=\"submit\" name=\"vote2Btn\" value=\"Vote for 2\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		
		
		// choice 3
		out.println("<div class=\"gallery\">");
		out.println("<img src=\"../lab3Assets/C++.png\"  width=\"200\" height=\"200\">");
		out.println("<div class=\"desc\">");
		out.println("<form action=\"Vote\" method=\"post\">");
		out.println("	<input type=\"submit\" name=\"vote3Btn\" value=\"Vote for 3\">");
		out.println("</form>");
		out.println("</div>");
		out.println("</div>");
		
		
		/////////// Buttons //////////

		out.println("</form>");

		// Store variables in the Application Scope (ServletContext)
		getServletContext().setAttribute("count1", count1);
		getServletContext().setAttribute("count2", count2);
		getServletContext().setAttribute("count3", count3);
		getServletContext().setAttribute("totalCount", totalCount);

		out.println("</div>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// btn1
		if (request.getParameter("vote1Btn") != null) {
			count1++;
			totalCount++;
			response.sendRedirect("Stats1");

		}

		// btn 2
		if (request.getParameter("vote2Btn") != null) {
			count2++;
			totalCount++;
			response.sendRedirect("Stats2");

		}

		// btn3
		if (request.getParameter("vote3Btn") != null) {
			count3++;
			totalCount++;
			response.sendRedirect("Stats3");

		}

		doGet(request, response);

	}

}
