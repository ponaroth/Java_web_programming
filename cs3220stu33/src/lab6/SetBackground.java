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

@WebServlet(urlPatterns = { "/lab6/SetBackground" }, loadOnStartup = 1)
public class SetBackground extends HttpServlet {
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
		String name = getName(request);

		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Set Background color</title>");
		out.println(
				"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("<style>");
		out.println("body {background-color: " + name + ";}");
		System.out.println("here " + name);

		out.println("</style>");

		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>Set Background</h1>");
		out.println("<p>for example #FFEE00</p>");
		out.println("<form action=\"SetBackground\" method=\"post\">");

//		if (name == null) {
		out.println("	color code: <input type=\"text\" name=\"name\"> <br>");
//		} else {
//			response.sendRedirect("ViewBackground");
//
//		}

		// out.println(" Password: <input type=\"text\" name=\"password\"> <br>");

		out.println("	<input type=\"submit\" name=\"setBtn\" value=\"Set Cookie color\">");
		out.println("<br>");

		out.println("</form>");

		out.println(" <a href=\"ViewBackground" + "\">Go to View Background page</a> ");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("setBtn") != null) {

			// Read the name and the message that was submitted by the form
			String name = request.getParameter("name");

			if (name != null) {
				System.out.println("here2 " + name);
				
				// Create a cookie named "bgColor" and store the User's name
				Cookie cookie = new Cookie("bgColor", name);

				cookie.setMaxAge(60 * 60 * 24 * 5);

				// Expose the cookie to any directory on our server
				// cookie.setPath("/");
				response.addCookie(cookie);

				response.sendRedirect("SetBackground");
			}
		}

		doGet(request, response);

	}

}
