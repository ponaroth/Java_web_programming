package tictactoe;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/GameController")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		GameBean theGame = new GameBean();
		getServletContext().setAttribute("theGame", theGame);
		getServletContext().setAttribute("Board", theGame.getBoard());
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the session
		HttpSession session = request.getSession();
		GameBean theGame = (GameBean) getServletContext().getAttribute("theGame");
		
		if (theGame == null) System.out.println("game is null");
		
		// Add the game to the SESSION Scope
		session.setAttribute("theGame", theGame);
		session.setAttribute("Board", theGame.getBoard());
		
		request.getRequestDispatcher("/tttJSP/GameView.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
