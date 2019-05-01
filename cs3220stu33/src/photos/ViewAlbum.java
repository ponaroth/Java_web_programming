package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/photos/ViewAlbum")
public class ViewAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private MyAlbum getEntry(int id) {
		
		// Get a reference to the guest book
		@SuppressWarnings("unchecked")
		ArrayList<MyAlbum> albumList2 = (ArrayList<MyAlbum>) getServletContext().getAttribute("albumList");
		
		
		// Find the entry that matches the specified ID
		for(MyAlbum entry : albumList2) {
			if (entry.getId() == id)
				return entry;
		}
		
		return null;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		MyAlbum entry = getEntry(id);
		
		// If we can't find the entry specified, send the user back to the guest book
		if(entry == null) {
			response.sendRedirect("albums");
			return;
		}
		
		
		response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        
        out.println("<head>");
        out.println("    <meta charset=\"UTF-8\">");
        out.println("    <title>View Album</title>");
        out.println("     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");
        
        out.println("<h1>View album: " + entry.getName() + "</h1>");
        out.println("<h5>you have " + entry.getPhotoList().size() + " photo(s)</h5>");
        out.println("</div>");
        
        out.println("<table class=\"card\">");
		out.println("  <tr>");
		out.println("    <th>Photo</th>");
		out.println("    <th>Name</th>");
//		out.println("    <th>Description</th>");
		out.println("    <th>Actions</th>");
		out.println("  </tr>");
    
		
		int count = 0;

		// print search results in a table
		for (Photo photo : entry.getPhotoList()) {
			out.println("    <td>" + "<input type=\"image\" src=\"Download\"/>" + "</td>");
			out.println("    <td>" + photo.getName() + "</td>");
			// out.println(" <td>" + markedNameList.get(count) + "</td>");
			// out.println(" <td> Edit | Delete </td>");
			out.println("    <td>");
//			out.println(" <a href=\"EditAlbum?id=" + entry.getId() + "\">Edit</a> ");
//			out.println(" | ");
			out.println(" <a href=\"ViewAlbum?id=" + photo.getPhotoID() + "\">View album</a> ");
			out.println(" | ");
			out.println(" <a href=\"DeleteAlbum?id=" + photo.getPhotoID() + "\">Delete</a>");
			out.println("    </td>");

			out.println("  </tr>");

			count++;

		}

		out.println("</table>");
		
		out.println("<a class=\"btn btn-primary\" href=\"Upload\">Upload photo</a>");

		out.println("</div>");
		out.println("<div>");

//		out.println("<input type=\"image\" src=\"Download\"/>");

		out.println("</div>");

		out.println("</body>");
		out.println("</html>");
		
		
		
       
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		
		int id = Integer.parseInt(request.getParameter("id"));
		MyAlbum entry = getEntry(id);
		
		entry.setName(name);
		entry.setMessage(message);
		
		// Go back to guest book
		response.sendRedirect("albums");
		
	}

}
