package hw4;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hw4.GuestBookEntry;

@WebServlet("/Add")
public class Add extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Add()
    {
        super();
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        request.getRequestDispatcher( "/WEB-INF/Add.jsp" )
            .forward( request, response );
    }
    
	@SuppressWarnings("unchecked")
    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        Connection c = null;
        try
        {
            String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu37?useSSL=false&allowPublicKeyRetrieval=true";
            String username = "cs3220stu37";
            String password = "7!oKx3BU";
        	
        	

            String sql = "insert into guestbook1 (name, specialties, presentation) values (?, ?, ?)";

            c = DriverManager.getConnection( url, username, password );
            PreparedStatement pstmt = c.prepareStatement( sql );
           // pstmt.setString( 1, request.getParameter( "id" ) );
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

}

//@WebServlet("/AddComment")
//public class Add extends HttpServlet {
//
//	private static final long serialVersionUID = 1L;
//
//	public Add() {
//		super();
//	}
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.getRequestDispatcher("/WEB-INF/Add.jsp").forward(request, response);
//	}
//
//	@SuppressWarnings("unchecked")
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");
//		entries.add(new GuestBookEntry( request.getParameter("name"), request.getParameter("specialties"),
//				request.getParameter("message")));
//		
//		response.sendRedirect("Hw4");
//	}
//
//}
