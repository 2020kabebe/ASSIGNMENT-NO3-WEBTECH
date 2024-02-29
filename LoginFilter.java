package servletPack;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import model.StudentModel;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/login")
public class LoginFilter extends HttpFilter implements Filter {
	
	private StudentModel studmodel = new StudentModel();
       
    public StudentModel getstudmodel() {
		return studmodel;
	}

	public void setstudmodel(StudentModel studmodel) {
		this.studmodel = studmodel;
	}

	/**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req = (HttpServletRequest) request;
		
		String email = req.getParameter("email").trim();
		String password = req.getParameter("password").trim();

		if(isValid(email) && password.length() >= 8) {
			
			System.out.println("From Filter LoginFilter");
			
			studmodel.setEmail(email);
			studmodel.setPassword(password);
			
			request.setAttribute("user_data",studmodel);

    		RequestDispatcher rd = request.getRequestDispatcher("login");
    		rd.forward(request, response);
		}
		else {
			System.out.println("Email or password is not valid");
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
