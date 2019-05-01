package RateMyLeturer;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RateMyLeturer.BookEntry;
import RateMyLeturer.RatingEntry;


/**
 * Servlet implementation class Rating
 */
@WebServlet("/Rating")
public class Rating extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rating() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
    public void init( ServletConfig config, ServletRequest request ) throws ServletException
    {
        super.init( config );
        
		ArrayList<RatingEntry> raterList = new ArrayList<RatingEntry>();
		
		Integer id = Integer.valueOf( request.getParameter( "id" ) );
        BookEntry entry = getEntry( id );

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("raterList", entry.raterList);
		

    }
    
    // this method get a GuestBookEntry by id and return the entry
    @SuppressWarnings("unchecked")
    private BookEntry getEntry( Integer id )
    {

        ArrayList<BookEntry> entries = (ArrayList<BookEntry>) getServletContext().getAttribute(
                "entries" );
        
        	
            for( BookEntry entry : entries ) {
                if( entry.getId().equals(id) ) { 
                	return entry;
                }
            }
    

		return null;
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	Integer id = Integer.valueOf( request.getParameter( "id" ) );
        BookEntry entry = getEntry(id);
        request.setAttribute( "entry", entry );
			
	    getServletContext().setAttribute( "raterList", entry.raterList );
	    
//		
      	request.getRequestDispatcher( "/WEB-INF/Rating.jsp" )
          .forward( request, response );

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		ArrayList<BookEntry> entries = (ArrayList<BookEntry>) getServletContext().getAttribute("entries");
	               
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        BookEntry entry = entries.get(id -1);
       
        double rating = 0;
        if (request.getParameter( "rating") == null) {
        	
        } else {
        	rating = Integer.parseInt(request.getParameter( "rating"));
        }	
        	
        String comment = request.getParameter( "comment" );
        String rater = request.getParameter( "rater");
        
       // System.out.println("133: "+ rating + " " + rater + " " + comment);
        
        entry.raterList.add(new RatingEntry(rating, rater, comment));
        
        entry.printRaterList();
       
        entry.setRating( entry.getRatingAvg());
        
        response.sendRedirect( "Main" );

//		
	}

}
