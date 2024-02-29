package servletPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class SessionManagerServlet
 */
@WebServlet("/LoginServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.print("\n\nHello from session manager\n\n");
		
		out.println("<html><body>");
		out.print("\n\n<a href='./index.html'>Go back to Login page</a>\n\n");
		out.println("</body></html>");	
		
		HttpSession session = request.getSession(false);
		
		if (session != null) {
			
			String studentmail = (String) session.getAttribute("emailAttribute");
			String studentpassword = (String) session.getAttribute("passwordAttribute");
			
			System.out.println("Your session has started " + studentmail + "\n\n");
			out.print("\n\nYour session has started "  + studentmail + "\n\n");
			response.sendRedirect("./assets_styles/home.html");	
			
			response.getWriter().println("<script>window.location='./assets_styles/home.html';</script>");
			
			// implements sending email			

			out.println("WELCOME TO YOUR ACCOUNT " + studentmail);
		}
		else {
			response.getWriter().println("<script>window.location='./index.html';</script>");
			response.sendRedirect("./index.html");
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
}
