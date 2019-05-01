package hw4;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import hw4.RatingEntry;
import hw4.GuestBookEntry;


@WebServlet("/Rating")
public class Rating extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int idSeed = 5;


	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config, ServletRequest request) throws ServletException {
		super.init(config);

		/*
		ArrayList<RatingEntry> raterList = new ArrayList<RatingEntry>();

		Integer id = Integer.valueOf(request.getParameter("id"));
		GuestBookEntry entry = getEntry(id);

		System.out.println("48 init: " + entry.getName());

		// Store the guest book in the Application Scope (ServletContext)
		getServletContext().setAttribute("raterList", entry.raterList);
		 */

	}

	// this method get a GuestBookEntry by id and return the entry
	/*
	@SuppressWarnings("unchecked")
	private GuestBookEntry getEntry(Integer id) {

		ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");

		for (GuestBookEntry entry : entries) {
			if (entry.getId().equals(id)) {

				System.out.println("67 id: " + id);
				System.out.println("68 entry: " + entry);
				return entry;
			}
		}

		return null;
	}
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer entryId = Integer.valueOf( request.getParameter( "id" ) );
		ArrayList<RatingEntry> ratings = new ArrayList<RatingEntry>();
		GuestBookEntry entry = new GuestBookEntry();



		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
			String username = "cs3220stu37";
			String password = "7!oKx3BU";
			

			c = DriverManager.getConnection( url, username, password );
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("select * from guestbook1 where id ='"+entryId+"'" );


			while( rs.next() )
				entry =  new GuestBookEntry(
						rs.getInt( "id" ), 
						rs.getString( "name" ), 
						rs.getString( "presentation" ), 
						rs.getString( "specialties" ),
						rs.getDouble( "rating" ) ) ;
			
			rs = stmt.executeQuery( "select * from rating1 where r_id ='"+entryId+"'"); 

			while( rs.next() ){
				ratings.add( new RatingEntry( 
						rs.getInt( "id" ),  
						rs.getDouble("rating"), 
						rs.getString("rater"), 
						rs.getString( "comment" )));
			}

			if(ratings.size() > 0) {
				entry.setRaterList(ratings);
			}


			c.close();
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}
		finally
		{
			try
			{
				if( c != null ) c.close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}
		}

		request.setAttribute( "entry", entry );
		request.getRequestDispatcher( "/WEB-INF/Rating.jsp" ).forward( request, response );


	}
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
			String username = "cs3220stu37";
			String password = "7!oKx3BU";


			String sql = "insert into rating1 (rating, rater, comment, r_id) values (?, ?, ?, ?)";

			c = DriverManager.getConnection( url, username, password );
			PreparedStatement pstmt = c.prepareStatement( sql );
			//pstmt.setString( 1, request.getParameter( "id1" ) );
			pstmt.setString( 1, request.getParameter( "rating" ) );
			pstmt.setString( 2, request.getParameter( "rater" ) );
			pstmt.setString( 3, request.getParameter( "comment" ) );
			pstmt.setString( 4, request.getParameter("id") );

			pstmt.executeUpdate();
			c.close();
			
		}
		catch( SQLException e )
		{
			throw new ServletException( e );
		}
		finally
		{
			try
			{
				if( c != null ) c.close();
			}
			catch( SQLException e )
			{
				throw new ServletException( e );
			}
		}

		response.sendRedirect("Hw4");


		//
		//		ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");
		//
		//		Integer id = Integer.valueOf(request.getParameter("id"));
		//		GuestBookEntry entry = entries.get(id - 1);
		//
		//		System.out.println("127: " + entry.raterList);
		//
		//		double rating = 0;
		//		if (request.getParameter("rating") == null) {
		//
		//		} else {
		//			rating = Integer.parseInt(request.getParameter("rating"));
		//		}
		//
		//		String comment = request.getParameter("comment");
		//		String rater = request.getParameter("rater");
		//
		//		System.out.println("133: " + rating + " " + comment + " " + rater);
		//
		//		entry.raterList.add(new RatingEntry(rating, rater, comment));
		//
		//		entry.printRaterList();
		//
		//		entry.setRating(entry.getRatingAvg());
		//
		//		response.sendRedirect("Hw4");

	}

}

//
//public class Add extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    public Add()
//    {
//        super();
//    }
//
//    protected void doGet( HttpServletRequest request,
//        HttpServletResponse response ) throws ServletException, IOException
//    {
//        request.getRequestDispatcher( "/WEB-INF/Add.jsp" )
//            .forward( request, response );
//    }
//    
//	@SuppressWarnings("unchecked")
//    protected void doPost( HttpServletRequest request,
//        HttpServletResponse response ) throws ServletException, IOException
//    {
//        Connection c = null;
//        try
//        {
//            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
//            String username = "cs3220stu37";
//            String password = "7!oKx3BU";
//
//            String sql = "insert into guestbook1 (id, name, specialties, message) values (?, ?, ?, ?)";
//
//            c = DriverManager.getConnection( url, username, password );
//            PreparedStatement pstmt = c.prepareStatement( sql );
//            pstmt.setString( 1, request.getParameter( "id" ) );
//            pstmt.setString( 2, request.getParameter( "name" ) );
//            pstmt.setString( 3, request.getParameter( "specialties" ) );
//            pstmt.setString( 4, request.getParameter( "message" ) );
//
//            pstmt.executeUpdate();
//            c.close();
//        }
//        catch( SQLException e )
//        {
//            throw new ServletException( e );
//        }
//        finally
//        {
//            try
//            {
//                if( c != null ) c.close();
//            }
//            catch( SQLException e )
//            {
//                throw new ServletException( e );
//            }
//        }
//
//		response.sendRedirect("Hw4");
//    }
//
//}
