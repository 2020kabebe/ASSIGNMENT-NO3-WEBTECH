package servletPack;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Part;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import dao.StudentDao;
import model.StudentModel;

/**
 * Servlet Filter implementation class SignupFilter
 */
@WebFilter("/register")
public class SignupFilter extends HttpFilter implements Filter {
	
	private static final long serialVersionUID = 1L;
	private StudentModel studmodel = new StudentModel();
	
	
	public SignupFilter(StudentModel studmodel) {
		super();
		this.studmodel = studmodel;
	}

	public StudentModel getstudmodel() {
		return studmodel;
	}

	public void setstudmodel(StudentModel studmodel) {
		this.studmodel = studmodel;
	}

    public SignupFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");

		// to upload files into db
		String certificateFile = request.getParameter("file");
		byte [] certfile = certificateFile.getBytes();
		
		String passportimage = request.getParameter("image");
		byte [] passimage = passportimage.getBytes();
		
		
		if (name.trim().length() < 6) {
			System.out.println("Name is not valid");
		}
		else if(!isValid(email)){
			System.out.println("Email not valid");
		}
		else if(phone.length() < 10 || phone.length() > 10) {
			System.out.println("Phone not valid");
		}
		else if (password.length() < 6) {
			System.out.println("Password is not valid");
		}
		else {
			System.out.println("Data filtered successfully");
			
			studmodel.setName(name);
			studmodel.setEmail(email);
			
			studmodel.setPhone(phone);
			studmodel.setPassword(password);
			
			studmodel.setCertificateFile(certfile);
			studmodel.setPassportimage(passimage);
			
			// send data to SignupServlet
			
			request.setAttribute("user_data",studmodel);
    		RequestDispatcher rd = request.getRequestDispatcher("register");
    		rd.forward(request, response);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	static boolean isValid(String email) {
	      String regex = "[a-zA-Z][\\w-]{1,20}@\\w{2,20}\\.\\w{2,3}$";
	     
	      Pattern pattern = Pattern.compile(regex);
	      Matcher matcher = pattern.matcher(email);
	      
	      return matcher.matches();
	}
}
