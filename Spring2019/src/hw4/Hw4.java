package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw4.GuestBookEntry;

@WebServlet(urlPatterns = "/Hw4")
public class Hw4 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Hw4()
	{
		super();
	}

	public void init( ServletConfig config ) throws ServletException
	{
		super.init( config );
	}



	protected void doGet( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
	{
		List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
		;
		Connection c = null;
		try
		{
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
			String username = "cs3220stu37";
			String password = "7!oKx3BU";
			

			c = DriverManager.getConnection( url, username, password );
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select * from guestbook1" );
			
			
			 
			while( rs.next() )
				entries.add( new GuestBookEntry(
						rs.getInt( "id" ), 
						rs.getString( "name" ), 
						rs.getString( "presentation" ), 
						rs.getString( "specialties" ),
						rs.getDouble( "rating" ) ) );

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

		request.setAttribute( "entries", entries );
		request.getRequestDispatcher( "/WEB-INF/Hw4.jsp" ).forward( request, response );
	}
	//    protected void doGet( HttpServletRequest request,
	//        HttpServletResponse response ) throws ServletException, IOException
	//    {
	//
	//    	request.getRequestDispatcher( "/WEB-INF/Hw4.jsp" )
	//            .forward( request, response );
	//    }

	protected void doPost( HttpServletRequest request,
			HttpServletResponse response ) throws ServletException, IOException
	{

		doGet( request, response );
	}

}

//@WebServlet(urlPatterns = "/GuestBook")
//public class GuestBook extends HttpServlet {
//
//    private static final long serialVersionUID = 1L;
//
//    public GuestBook()
//    {
//        super();
//    }
//
//    public void init( ServletConfig config ) throws ServletException
//    {
//        super.init( config );
//
//        try
//        {
//            Class.forName( "com.mysql.jdbc.Driver" );
//        }
//        catch( ClassNotFoundException e )
//        {
//            throw new ServletException( e );
//        }
//    }
//
//    protected void doGet( HttpServletRequest request,
//        HttpServletResponse response ) throws ServletException, IOException
//    {
//        List<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
//        Connection c = null;
//        try
//        {
//            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
//            String username = "cs3220stu37";
//            String password = "7!oKx3BU";
//
//            c = DriverManager.getConnection( url, username, password );
//            Statement stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "select * from guestbook" );
//
//            while( rs.next() )
//                entries.add( new GuestBookEntry( rs.getInt( "id" ),
//                    rs.getString( "name" ), rs.getString( "message" ),
//                    rs.getTimestamp( "date" ) ) );
//
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
//        request.setAttribute( "entries", entries );
//        request.getRequestDispatcher( "/WEB-INF/GuestBook.jsp" )
//            .forward( request, response );
//    }
//
//    protected void doPost( HttpServletRequest request,
//        HttpServletResponse response ) throws ServletException, IOException
//    {
//        doGet( request, response );
//    }
//
//}
