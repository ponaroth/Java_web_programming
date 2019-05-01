package lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/lab3/GuestBook" }, loadOnStartup = 1)
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a local array list of guest book entries
		ArrayList<MyGuestBookEntry> guestbookEntries = new ArrayList<MyGuestBookEntry>();

		// Pre-populate the guest book with some entries

		guestbookEntries.add(new MyGuestBookEntry("Dean", "It highlight but case sensitive ReeeeEEEEE."));
		guestbookEntries.add(new MyGuestBookEntry("Dean", "Can I get partial credit? please"));
		guestbookEntries.add(new MyGuestBookEntry("John Doe", "Hello, World!"));
		guestbookEntries.add(new MyGuestBookEntry("Mary Jane", "Hi!"));
		guestbookEntries.add(new MyGuestBookEntry("Joe Boxer", "Howdy."));

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("guestbookEntries", guestbookEntries);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// save search
		String pSearchText = "";
		String pSearchType = "";

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
		out.println("    <title>Guest Book</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h5><mark>It highlights but case sensitive. Can I get partial credit?</mark></h5>");
		out.println("<div class=\"container\">");

		out.println("<h1>Guest Book</h1>");

		out.println("<form action=\"GuestBook\" method=\"get\">");
		out.println("  <input type=\"text\" name=\"searchQuery\" value= " + pSearchText + ">");

		////////////// sticky search type
		out.println("  <select name=\"searchType\">");

		if (pSearchType.equals("Name")) {
			System.out.println(1);

			out.println("    <option>ID</option>");
			out.println("    <option selected>Name</option>");

			out.println("    <option>Message</option>");
			out.println("    <option>All Text Fields</option>");
		} else if (pSearchType.equals("ID")) {
			System.out.println(0);

			out.println("    <option selected>ID</option>");
			out.println("    <option>Name</option>");

			out.println("    <option>Message</option>");
			out.println("    <option>All Text Fields</option>");

		} else if (pSearchType.equals("Message")) {
			System.out.println(2);

			out.println("    <option>ID</option>");
			out.println("    <option>Name</option>");

			out.println("    <option selected>Message</option>");
			out.println("    <option>All Text Fields</option>");

		} else if (pSearchType.equals("All Text Fields")) {
			System.out.println(3);

			out.println("    <option>ID</option>");
			out.println("    <option>Name</option>");
			out.println("    <option>Message</option>");
			out.println("    <option selected>All Text Fields</option>");

		} else {
			System.out.println("else");

			out.println("    <option>ID</option>");
			out.println("    <option selected>Name</option>");
			out.println("    <option>Message</option>");
			out.println("    <option>All Text Fields</option>");
		}

		pSearchType = "";

		out.println("  </select>");

		/////////////

		out.println("  <input type=\"submit\" name=\"searchBtn\" value=\"Search\">");
		out.println("</form>");

		out.println("<hr>");

		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println("  <tr>");
		out.println("    <th>ID</th>");
		out.println("    <th>Name</th>");
		out.println("    <th>Message</th>");
		out.println("    <th>Actions</th>");
		out.println("  </tr>");

		// Get a reference to the guestbook
		ArrayList<MyGuestBookEntry> guestbookEntries = (ArrayList<MyGuestBookEntry>) getServletContext()
				.getAttribute("guestbookEntries");

		// create an arrayList that store markedTexts (only the ones that was searched)

		// Was a search submitted?
		if (request.getParameter("searchBtn") != null) {
			String searchType = request.getParameter("searchType");
			pSearchType = searchType;

			// turn searchQuery into string
			String searchText = (String) request.getParameter("searchQuery");

			String subText = searchText.toLowerCase();

			// If we are searching by ID, execute this code block
			if (searchType.equals("ID")) {
				// If searching by ID, the 'searchQuery' parameter should contain an integer
				// So, we parse the integer out of the string
				int id = Integer.parseInt(request.getParameter("searchQuery"));

				// Create a new array list to store our search results
				ArrayList<MyGuestBookEntry> searchResults = new ArrayList<MyGuestBookEntry>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyGuestBookEntry entry : guestbookEntries) {
					if (entry.getId() == id) {
						searchResults.add(entry);
						break;
					}
				}

				guestbookEntries = searchResults;

			}

			// If we are searching by name, execute this code block
			if (searchType.equals("Name")) {

				// Create a new array list to store our search results
				ArrayList<MyGuestBookEntry> searchResults = new ArrayList<MyGuestBookEntry>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyGuestBookEntry entry : guestbookEntries) {
					if (entry.getName().toLowerCase().contains(subText)) {

						if (request.getParameter("searchQuery") != "") {

							String keyWord = request.getParameter("searchQuery");

							entry.setMarkedName(entry.highLight(entry.getName(), keyWord));

						}

						searchResults.add(entry);

					}

				}

				guestbookEntries = searchResults;
			}

			// If we are searching by message, execute this code block
			if (searchType.equals("Message")) {

				// Create a new array list to store our search results
				ArrayList<MyGuestBookEntry> searchResults = new ArrayList<MyGuestBookEntry>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyGuestBookEntry entry : guestbookEntries) {
					if (entry.getMessage().toLowerCase().contains(subText)) {

						if (request.getParameter("searchQuery") != "") {
							String keyWord = request.getParameter("searchQuery");

							entry.setMarkedMessage(entry.highLight(entry.getMessage(), keyWord));

						}

						searchResults.add(entry);

					}

				}

				guestbookEntries = searchResults;
			}

			// If we are searching by all text fields, execute this code block
			if (searchType.equals("All Text Fields")) {

				// Create a new array list to store our search results
				ArrayList<MyGuestBookEntry> searchResults = new ArrayList<MyGuestBookEntry>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyGuestBookEntry entry : guestbookEntries) {
					if (entry.getName().toLowerCase().contains(subText)
							|| entry.getMessage().toLowerCase().contains(subText)) {
						if (request.getParameter("searchQuery") != "") {

							String keyWord = request.getParameter("searchQuery");

							entry.setMarkedName(entry.highLight(entry.getName(), keyWord));
							entry.setMarkedMessage(entry.highLight(entry.getMessage(), keyWord));

						}

						searchResults.add(entry);

					}

				}

				guestbookEntries = searchResults;
			}

		}

		int count = 0;

		// print search results in a table
		for (MyGuestBookEntry entry : guestbookEntries) {
			out.println("  <tr>");
			out.println("    <td>" + entry.getId() + "</td>");
			out.println("    <td>" + entry.getMarkedName() + "</td>");
			// out.println(" <td>" + markedNameList.get(count) + "</td>");
			out.println("    <td>" + entry.getMarkedMessage() + "</td>");
			//out.println(" <td> Edit | Delete </td>");
			out.println("    <td>");
			out.println(" <a href=\"EditComment?id=" + entry.getId() + "\">Edit</a> ");
			out.println(" | ");
			out.println(" <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete</a>");
			out.println("    </td>");

			out.println("  </tr>");

			count++;

		}

		out.println("</table>");

		out.println("<a class=\"btn btn-primary\" href=\"AddComment\">Add Comment</a>");

		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
