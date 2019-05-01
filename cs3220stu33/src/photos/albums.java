package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/photos/albums" }, loadOnStartup = 1)
public class albums extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Create an array list of albums
		ArrayList<MyAlbum> albumList = new ArrayList<MyAlbum>();
		
		albumList.add(new MyAlbum("Party", "fun 2018"));
		

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("albumList", albumList);

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
		out.println("    <title>Album Home Page</title>");

		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");

		out.println("<h1>Album Home Page</h1>");
		ArrayList<MyAlbum> temp = (ArrayList<MyAlbum>) getServletContext().getAttribute("albumList");
		out.println("<h5>you have " + temp.size() + " album(s)<h5>");

//		out.println("<form action=\"albums\" method=\"get\">");
//		out.println("  <input type=\"text\" name=\"searchQuery\" value= " + pSearchText + ">");

		////////////// sticky search type
//		out.println("  <select name=\"searchType\">");

		if (pSearchType.equals("Name")) {

//			out.println("    <option>ID</option>");
//			out.println("    <option selected>Name</option>");
//
//			out.println("    <option>Message</option>");
//			out.println("    <option>All Text Fields</option>");
		} else if (pSearchType.equals("ID")) {
			System.out.println(0);

//			out.println("    <option selected>ID</option>");
//			out.println("    <option>Name</option>");
//
//			out.println("    <option>Message</option>");
//			out.println("    <option>All Text Fields</option>");

		} else if (pSearchType.equals("Message")) {
			System.out.println(2);

//			out.println("    <option>ID</option>");
//			out.println("    <option>Name</option>");
//
//			out.println("    <option selected>Message</option>");
//			out.println("    <option>All Text Fields</option>");

		} else if (pSearchType.equals("All Text Fields")) {
			System.out.println(3);

//			out.println("    <option>ID</option>");
//			out.println("    <option>Name</option>");
//
//			out.println("    <option>Message</option>");
//			out.println("    <option selected>All Text Fields</option>");

		} else {

//			out.println("    <option>ID</option>");
//			out.println("    <option selected>Name</option>");
//
//			out.println("    <option>Message</option>");
//			out.println("    <option>All Text Fields</option>");
		}

		pSearchType = "";

		out.println("  </select>");

		/////////////

//		out.println("  <input type=\"submit\" name=\"searchBtn\" value=\"Search\">");
//		out.println("</form>");

		// Get a reference to the guestbook
		ArrayList<MyAlbum> albumList = (ArrayList<MyAlbum>) getServletContext().getAttribute("albumList");

//////SEARCH FUNCTION/////////////////////////////////////////////////////////////////////////////////////////////////		
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
				ArrayList<MyAlbum> searchResults = new ArrayList<MyAlbum>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyAlbum entry : albumList) {
					if (entry.getId() == id) {
						searchResults.add(entry);
						break;
					}
				}

				albumList = searchResults;

			}

			// If we are searching by name, execute this code block
			if (searchType.equals("Name")) {

				// Create a new array list to store our search results
				ArrayList<MyAlbum> searchResults = new ArrayList<MyAlbum>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyAlbum entry : albumList) {
					if (entry.getName().toLowerCase().contains(subText)) {

						if (request.getParameter("searchQuery") != "") {

							String keyWord = request.getParameter("searchQuery");

							entry.setMarkedName(entry.highLight(entry.getName(), keyWord));

						}

						searchResults.add(entry);

					}

				}

				albumList = searchResults;
			}

			// If we are searching by message, execute this code block
			if (searchType.equals("Message")) {

				// Create a new array list to store our search results
				ArrayList<MyAlbum> searchResults = new ArrayList<MyAlbum>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyAlbum entry : albumList) {
					if (entry.getMessage().toLowerCase().contains(subText)) {

						if (request.getParameter("searchQuery") != "") {
							String keyWord = request.getParameter("searchQuery");

							entry.setMarkedMessage(entry.highLight(entry.getMessage(), keyWord));

						}

						searchResults.add(entry);

					}

				}

				albumList = searchResults;
			}

			// If we are searching by all text fields, execute this code block
			if (searchType.equals("All Text Fields")) {

				// Create a new array list to store our search results
				ArrayList<MyAlbum> searchResults = new ArrayList<MyAlbum>();

				// Iterate over EVERY guest book entry, and those that match the search criteria
				// will be added to our search results array list
				for (MyAlbum entry : albumList) {
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

				albumList = searchResults;
			}

		}

////////////////////////////////////////////////////////////////////////////////////////////////////////
		int count = 0;

		// print search results in a table
		for (MyAlbum entry : albumList) {
			out.println("<div class = \"card\">");
			out.println("    <b>" + "<input type=\"image\" width = 15% src=\"Download\"/>" + "</b>");

			out.println("<div class = \"card\">");
			out.println("    <b>" + entry.getMarkedName() + "</b>");
			// out.println(" <td>" + markedNameList.get(count) + "</td>");
			
			out.println("    <p>" + entry.getMarkedMessage() + "</p>");
			// out.println(" <td> Edit | Delete </td>");
			out.println("    <b>");
//			out.println(" <a href=\"EditAlbum?id=" + entry.getId() + "\">Edit</a> ");
//			out.println(" | ");
			out.println("<div>");
			out.println(" <a href=\"ViewAlbum?id=" + entry.getId() + "\">View album</a> ");
			out.println(" | ");
			out.println(" <a href=\"DeleteAlbum?id=" + entry.getId() + "\">Delete</a>");
			out.println("    </b>");
			out.println("  </b>");
			out.println("</div>");


			out.println("</div>");

			count++;

		}

		out.println("</div>");
		out.println("<br>");
		
		out.println("<a class=\"btn btn-primary\" href=\"AddAlbum\">Add Album</a>");

//		out.println("<input type=\"image\" src=\"Download\"/>");

		out.println("</br>");

		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
