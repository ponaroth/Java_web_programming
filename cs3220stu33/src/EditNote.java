

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditNote")
public class EditNote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MyAlbum getEntry(int id) {

		// Get a reference to the guest book
		ArrayList<MyAlbum> noteList = (ArrayList<MyAlbum>) getServletContext().getAttribute("noteList");

		// Find the entry that matches the specified ID
		for (MyAlbum entry : noteList) {
			if (entry.getId() == id)
				return entry;
		}

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		MyAlbum entry = getEntry(id);

		// If we can't find the entry specified, send the user back to the guest book
		if (entry == null) {
			response.sendRedirect("GuestBook");
			return;
		}

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Edit Note</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>My Notes</h1>");

		out.println("<form action=\"EditNote\" method=\"post\">");
		if (request.getAttribute("nameError") != null)
			out.println("   <p class=\"text-danger\">Error: You must enter a title!</p>");
		out.println(" 	Note Title: <input type=\"text\" name=\"name\" value=\"" + entry.getName() + "\"> <br>");
		out.println("<br></br>");
		out.println(" 	Note Text: ");

		if (request.getAttribute("messageError") != null)
			out.println("   <p class=\"text-danger\">Error: You must enter text!</p>");
		out.println(" 	<textarea name=\"message\" rows=8 cols =25>" + entry.getMessage() + "</textarea>");
		out.println("   <input type=\"hidden\" name=\"id\" value=\"" + entry.getId() + "\">");
		out.println("<br></br>");
		out.println(" 	<input type=\"submit\" name=\"submitBtn\" value=\"Save Changes\">");

		out.println("</form>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		String message = request.getParameter("message");

		int id = Integer.parseInt(request.getParameter("id"));
		MyAlbum entry = getEntry(id);

		boolean isValidName = name != null && name.trim().length() > 0;
		boolean isValidMessage = message != null && message.trim().length() > 0;

		if (isValidName && isValidMessage) {

			entry.setName(name);
			entry.setMessage(message);

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
