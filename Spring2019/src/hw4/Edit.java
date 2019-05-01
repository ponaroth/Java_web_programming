package hw4;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw4.GuestBookEntry;


@WebServlet("/Edit")
public class Edit extends HttpServlet {

    private static final long serialVersionUentryId = 1L;

    public Edit()
    {
        super();
    }



    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	Integer entryId = Integer.valueOf( request.getParameter( "id" ) );
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
		request.getRequestDispatcher( "/WEB-INF/Edit.jsp" ).forward( request, response );
	}
    

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	Connection c = null;
        try
        {
           String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
           String username = "cs3220stu37";
           String password = "7!oKx3BU";
            
        	
        	
            Integer entryId = Integer.valueOf( request.getParameter( "id" ) );
            

            
            String sql = "UPDATE guestBook1 SET name=?, specialties=?, presentation=?  Where  id='"+entryId+"'";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
            //pstmt.setString( 1, request.getParameter( "id" ) );
            pstmt.setString( 1, request.getParameter( "name" ) );
            pstmt.setString( 2, request.getParameter( "specialties" ) );
            pstmt.setString( 3, request.getParameter( "presentation" ) );

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
    }

//        Integer entryId = Integer.valueOf( request.getParameter( "entryId" ) );
//        GuestBookEntry entry = getEntry( entryId );
//
//        entry.setName( request.getParameter( "name") );
//        entry.setMessage( request.getParameter( "message" ) );
//        entry.setSpecialties( request.getParameter( "specialties" ) );
//        
//
//        response.sendRedirect( "Hw4" );

 //   }

}
