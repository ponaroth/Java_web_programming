package RateMyLeturer;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RateMyLeturer.BookEntry;

@WebServlet("/AddComment")
public class Add extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Add() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Add.jsp").forward(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<BookEntry> entries = (ArrayList<BookEntry>) getServletContext().getAttribute("entries");
		entries.add(new BookEntry( request.getParameter("name"), request.getParameter("specialties"),
				request.getParameter("message")));
		
		response.sendRedirect("Main");
	}

}
