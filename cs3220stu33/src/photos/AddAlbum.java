package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddAlbum
 */
@WebServlet(urlPatterns = "/photos/AddAlbum")
public class AddAlbum extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Add Album</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

//        String name = request.getParameter("name");
//        String message = request.getParameter("message");

//        if (name == null)
//        	name = "";

//        message = message == null ? "" : message;

		out.println("<h1>Add Album</h1>");
		out.println("<form action=\"AddAlbum\" method=\"post\">");
		out.println(" 	Title: <input type=\"text\" name=\"name\" value=\"" + "" + "\"> <br>");

		if (request.getAttribute("nameError") != null)
			out.println("   <p class=\"text-danger\">Please enter a title</p>");

		out.println(" 	Description: <br>");
		out.println(" 	<textarea name=\"message\">" + "" + "</textarea><br>");

		if (request.getAttribute("messageError") != null)
			out.println("   <p class=\"text-danger\">Please enter a description</p>");

		out.println(" 	<input type=\"submit\" name=\"submitBtn\" value=\"Add Album\">");
		out.println("</form>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Read the values of name and message from the request
		String name = request.getParameter("name");
		String message = request.getParameter("message");

		boolean isValidName = name != null && name.trim().length() > 0;
		boolean isValidMessage = message != null && message.trim().length() > 0;

		if (isValidName && isValidMessage) {
			// Get a reference to the guest book
			ArrayList<MyAlbum> albumList = (ArrayList<MyAlbum>) getServletContext().getAttribute("albumList");

			// Add a new entry
			albumList.add(new MyAlbum(name, message));

			// Redirect the User back to the main page
			response.sendRedirect("albums");
		} else {

			if (!isValidName)
				request.setAttribute("nameError", true);

			if (!isValidMessage)
				request.setAttribute("messageError", true);

			doGet(request, response);
			return;

		}

	}

}
