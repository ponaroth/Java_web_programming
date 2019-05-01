package ttt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/PlayController")
public class PlayController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
		
		System.out.println("id is " + id);
		
		GameBean theGame = (GameBean) request.getSession().getAttribute("theGame");
		//tttBean theGame = (tttBean) getServletContext().getAttribute("theGame");
		
		if (theGame == null) System.out.println("game is null");
		
		theGame.updateGameStatus(id);
//		theGame.printBoard();

		response.sendRedirect("GameController");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

}
