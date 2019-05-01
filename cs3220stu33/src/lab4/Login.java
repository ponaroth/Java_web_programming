package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lab4/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String getName(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals("e-mail"))
					return cookie.getValue();

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set my content type
		response.setContentType("text/html");

		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();

		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Log in</title>");
		out.println(
				"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>Log in</h1>");
		out.println("<form action=\"Login\" method=\"post\">");

		String name = getName(request);

		if (name == null) {
			out.println("	Email: <input type=\"text\" name=\"name\"> <br>");
			

		} else {
			System.out.println("here");
			response.sendRedirect("Members_only");

		}

		out.println("	Password: <input type=\"text\" name=\"password\"> <br>");
		out.println("	<input type=\"submit\" name=\"submitBtn\" value=\"sign in\">");
		out.println("</form>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submitBtn") != null) {
			

			// Read the name and the message that was submitted by the form
			String name = request.getParameter("name");
			String password = request.getParameter("password");

			String correctEmail = "1";
			String correctPassword = "2";

			if (name == null)
				name = getName(request);

			boolean isValidName = name != null && name.trim().length() > 0;
			boolean isValidPassword = password != null && password.trim().length() > 0;

			// Add new entry to the guest book
			if (isValidName && isValidPassword) {

				if (name.equals(correctEmail) && password.equals(correctPassword)) {

					// Create a cookie named "e-mail" and store the User's name
					Cookie cookie = new Cookie("e-mail", name);

					// Expose the cookie to any directory on our server
					// cookie.setPath("/");

					response.addCookie(cookie);

				}

			} else {

				// Problem with the name?
				if (!isValidName)
					request.setAttribute("nameError", "Invalid Name");

				if (!isValidPassword)
					request.setAttribute("passwordError", "Invalid Password");

				doGet(request, response);
				return;
			}

		}

		// Redirect the User back to the login page
		response.sendRedirect("Members_only");
		
		
	}

}
