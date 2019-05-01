package midterm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/midterm/SortByDislikes")
public class SortByDislikes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		ArrayList<TaskEntry2> list1 = (ArrayList<TaskEntry2>) getServletContext().getAttribute("listA");
				
		//sorting using interfaces
				Collections.sort(list1, new Comparator<TaskEntry2>(){
				     public int compare(TaskEntry2 a, TaskEntry2 b){
				         if(a.dislike == b.dislike)
				             return 0;
				         return a.dislike < b.dislike ? 1 : -1;
				     }
				});
		
		
		// Redirect the User back to the main page
		response.sendRedirect("home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
