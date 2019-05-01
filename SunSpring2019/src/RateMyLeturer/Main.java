package RateMyLeturer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import RateMyLeturer.BookEntry;

@WebServlet(urlPatterns = "/Main")
public class Main extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Main()
    {
        super();
    }

    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        List<BookEntry> entries = new ArrayList<BookEntry>();
        entries.add( new BookEntry( "John", "10am on 2/20 in ET A227", "Machine Learning") );
        entries.add( new BookEntry( "Jack", "10am on 2/25 in ET A332","Computer Vision") );
        entries.add( new BookEntry( "Jane", "3pm on 2/27 in ET A126","Machine Learning") );
        entries.add( new BookEntry( "May", "3:30pm on 3/11 in FA 219","Computer Science Education") );
 
        getServletContext().setAttribute( "entries", entries );
    }

    protected void doGet( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {

    	request.getRequestDispatcher( "/WEB-INF/main.jsp" ).forward( request, response );
    }

    protected void doPost( HttpServletRequest request,
        HttpServletResponse response ) throws ServletException, IOException
    {

        doGet( request, response );
    }

}