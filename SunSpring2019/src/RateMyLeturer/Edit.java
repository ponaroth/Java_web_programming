package RateMyLeturer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RateMyLeturer.BookEntry;


@WebServlet("/Edit")
public class Edit extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Edit()
    {
        super();
    }

    /**
     * Given an id, retrieve the GuestBookEntry.
     */
    @SuppressWarnings("unchecked")
    private BookEntry getEntry( Integer id )
    {
        List<BookEntry> entries = (List<BookEntry>) getServletContext().getAttribute(
            "entries" );

        for( BookEntry entry : entries )
            if( entry.getId().equals( id ) ) return entry;

        return null;
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
    	Integer id = Integer.valueOf( request.getParameter( "id" ) );
      BookEntry entry = getEntry( id );
      request.setAttribute( "entry", entry );
    	
    	request.getRequestDispatcher( "/WEB-INF/Edit.jsp" )
        .forward( request, response );
      
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {
        Integer id = Integer.valueOf( request.getParameter( "id" ) );
        BookEntry entry = getEntry( id );

        entry.setName( request.getParameter( "name") );
        entry.setMessage( request.getParameter( "message" ) );
        entry.setSpecialties( request.getParameter( "specialties" ) );
        

        response.sendRedirect( "Main" );

    }

}
