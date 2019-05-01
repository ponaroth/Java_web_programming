package lab14.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;

/**
 * Servlet implementation class Lab14
 */
@WebServlet("/Team")

public class Team extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	ArrayList<Bracketology> Teams = new ArrayList<Bracketology>();

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		
		repopulate();
	

	}
	
	public void repopulate() throws ServletException {
		
		try {
			Scanner in;
			in = new Scanner(new File(getServletContext().getRealPath("/WEB-INF/teams.txt")));
			while (in.hasNextLine()) {
				String T1 = in.nextLine();
				String T2 = in.nextLine();
				Teams.add(new Bracketology(T1, T2));

			}
			in.close();
		} catch (FileNotFoundException e) {
			throw new ServletException(e);
		}
		getServletContext().setAttribute("Teams", Teams);
	}
	
	

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getAttribute("Teams");
		request.getRequestDispatcher("/WEB-INF/Bracketology.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Bracketology> matches = new ArrayList<Bracketology>();

		Map<String, String[]> p = request.getParameterMap();

		String[] keys = p.keySet().toArray(new String[p.size()]);
		for (int i = 0; i < keys.length; i += 2) {
			if (keys.length == 1) { // only one team chosen
				String winner = p.get(keys[i])[0];

				request.getSession().setAttribute("x", winner);
				request.getRequestDispatcher("/WEB-INF/FinalRound.jsp").forward(request, response);
				return;

			} 
			
			if (p.get(keys[i])[0].equals(Teams.get(i).team1)) {
				
				Teams.get(i).setTeam2(p.get(keys[i+1])[0]);
			} else {
				Teams.get(i).setTeam1(p.get(keys[i+1])[0]);
				
			}

			matches.add(new Bracketology(p.get(keys[i])[0], p.get(keys[i + 1])[0]));

			
		}
		
		// hard code team removal
		if (Teams.size() == 4 ) {
			
			Teams.remove(1);
			Teams.remove(2);
		} else if (Teams.size() == 2) {
			Teams.remove(1);
		} else if (Teams.size() == 1) {
			Teams.clear();
			repopulate();
			System.out.println("125: " + Teams.size());
		} 
		

		getServletContext().setAttribute("matches", matches);
		request.getRequestDispatcher("WEB-INF/Bracketology.jsp").forward(request, response);

	}
}
