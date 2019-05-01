package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/lab4/Members_only")
public class Members_only extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String getName( HttpServletRequest request )
    {
        Cookie[] cookies = request.getCookies();
        if( cookies != null )
            for( Cookie cookie : cookies )
                if( cookie.getName().equals( "e-mail" ) )
                    return cookie.getValue();

        return null;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		// Set my content type
		response.setContentType("text/html");
		
		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();
		
		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Member's only</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		
		
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		out.println("<h1>Member's only</h1>");
		
		String name = getName(request);
		
		if (name == null)
			response.sendRedirect("Login");
		else
			out.println("<strong>Hi, " + name + "</strong> <br>");
		
		
				
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
