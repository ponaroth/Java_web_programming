package lab6;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab6/ViewBackground")
public class ViewBackground extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals("bgColor"))
					return cookie.getValue();

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set my content type
		response.setContentType("text/html");

		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();

		// get cookie value
		String name = getName(request);

		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>View BG color</title>");
		out.println(
				"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");

		out.println("<style>");
		out.println("body {background-color: " + name + ";}");

		out.println("</style>");

		out.println("</head>");

		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>View Background</h1>");

//		if (name == "" || name == null)
//			response.sendRedirect("SetBackground");
//		else
		out.println("<strong>Your color code is " + name + "</strong> <br>");

		out.println("<form action=\"ViewBackground\" method=\"post\">");

		out.println("<input type=\"submit\" name=\"clearBtn\" value=\"Clear color cookie\">");
		out.println("<br>");

		out.println("</form>");
		
		out.println(" <a href=\"SetBackground" + "\">Go back</a> ");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("clearBtn") != null) {

			Cookie[] cookies = request.getCookies();
			
//			if (cookies != null)
//				for (Cookie cookie : cookies)
//					if (cookie.getName().equals("bgColor")) {
//						cookie.setValue("");
//						cookie.setPath("/");
//						cookie.setMaxAge(0);
//						response.addCookie(cookie);
//						System.out.println("here");
//						
//					}
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {			
					//cookie.setValue("");
					//cookie.setPath("/");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					//System.out.println("here");
					break;
				}
			}

			response.sendRedirect("ViewBackground");

		}

		doGet(request, response);

	}

}
