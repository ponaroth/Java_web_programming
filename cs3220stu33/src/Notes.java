

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Notes" }, loadOnStartup = 1)
public class Notes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<MyAlbum> noteList = new ArrayList<MyAlbum>();

		// Pre-populate the guest book with some entries


		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("noteList", noteList);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// save search
		String pSearchText = "";
		String pSearchType = "All Text Fields";

		if (request.getParameter("searchQuery") != null) {
			pSearchText = request.getParameter("searchQuery");
		}

		if (request.getParameter("searchType") != null) {
			pSearchType = request.getParameter("searchType");
		}

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>My Notes</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>My Notes</h1>");
		out.println("<a class=\"btn btn-primary\" style=\"float:right\" href=\"AddNote\">Create a Note</a>");
		
		ArrayList<MyAlbum> temp = (ArrayList<MyAlbum>) getServletContext().getAttribute("noteList");
		if (temp.size() == 0) {
			out.println("<h5>Select a Note from the list, or create a new Note<h5>");
		}

		out.println("<h5>Search Notes<h5>");

		out.println("<form action=\"Notes\" method=\"get\">");
		out.println("  <input type=\"text\" name=\"searchQuery\">");
		out.println("<h5> </h5>");
		out.println("<div><input type=\"submit\" name=\"searchBtn\" value=\"Search\" > </div>");
		out.println("</form>");

		out.println("<table class=\"table table-bordered table-striped table-hover\">");
//		out.println("  <tr>");
//		out.println("    <th></th>");
//		out.println("    <th></th>");
//		out.println("    <th></th>");
//		out.println("  </tr>");

		// Get a reference to the guestbook
		ArrayList<MyAlbum> noteList = (ArrayList<MyAlbum>) getServletContext().getAttribute("noteList");

		// create an arrayList that store markedTexts (only the ones that was searched)

		// Was a search submitted?
		if (request.getParameter("searchBtn") != null) {
			String searchType = "All Text Fields";
			pSearchType = searchType;

			// turn searchQuery into string
			String searchText = (String) request.getParameter("searchQuery");

			String subText = searchText.toLowerCase();

			// If we are searching by all text fields, execute this code block

			// Create a new array list to store our search results
			ArrayList<MyAlbum> searchResults = new ArrayList<MyAlbum>();

			// Iterate over EVERY guest book entry, and those that match the search criteria
			// will be added to our search results array list
			for (MyAlbum entry : noteList) {
				if (entry.getName().toLowerCase().contains(subText)
						|| entry.getMessage().toLowerCase().contains(subText)) {
					if (request.getParameter("searchQuery") != "") {

						String keyWord = request.getParameter("searchQuery");

						entry.setMarkedName(entry.highLight(entry.getName(), keyWord));
						entry.setMarkedMessage(entry.highLight(entry.getMessage(), keyWord));

					}

					searchResults.add(entry);

				}

				noteList = searchResults;
			}

		}

		int count = 0;
		

		// print search results in a table
		for (MyAlbum entry : noteList) {
			out.println("  <tr>");
			// out.println(" <td>" + entry.getId() + "</td>");
			out.println("    <td><u>" + entry.getName() + "</u></td>");
			// out.println(" <td>" + markedNameList.get(count) + "</td>");
			out.println("    <td>" + entry.getMessage() + "</td>");
			// out.println(" <td> Edit | Delete </td>");
			out.println("    <td>");
			out.println(" <a href=\"EditNote?id=" + entry.getId() + "\">Edit</a> ");
			out.println(" | ");
			out.println(" <a href=\"DeleteNote?id=" + entry.getId() + "\">Delete</a>");
			out.println("    </td>");

			out.println("  </tr>");

			count++;

		}

		out.println("</table>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
