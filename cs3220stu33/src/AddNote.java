


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddNote")
public class AddNote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Add Note</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		String name = request.getParameter("name");
		String message = request.getParameter("message");

		if (name == null)
			name = "";

		message = message == null ? "" : message;

		out.println("<h1>My Notes</h1>");
		if (request.getAttribute("nameError") != null)
			out.println("   <p class=\"text-danger\">Error: You must enter a title!</p>");
		out.println("<form action=\"AddNote\" method=\"post\">");
		out.println(" 	Note Title: <input type=\"text\" name=\"name\" value=\"" + name + "\"> <br>");
		out.println("<br></br>");

		if (request.getAttribute("messageError") != null)
			out.println("   <p class=\"text-danger\">Error: You must enter text!</p>");
		out.println(" 	Note Text: ");
		out.println(" 	<textarea name=\"message\" rows=8 cols =25>" + message + "</textarea>");

		out.println("<br></br>");

		out.println(" 	<input type=\"submit\" name=\"submitBtn\" value=\"Create Note\">");
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
			ArrayList<MyAlbum> noteList = (ArrayList<MyAlbum>) getServletContext().getAttribute("noteList");

			// Add a new entry
			noteList.add(new MyAlbum(name, message));

			// Redirect the User back to the main page
			response.sendRedirect("Notes");
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
