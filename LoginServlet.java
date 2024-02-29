package servletPack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import model.StudentModel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		StudentModel data = (StudentModel) request.getAttribute("Student_data");
		
		StudentDao dao = new StudentDao();
		StudentModel Studentmod = dao.findByStudentEmail(data.getEmail());

        if(Studentmod == null){
            response.sendRedirect("./index.html");
        }else if(Studentmod.getPassword().equals(data.getPassword())) {
        	
        	System.out.println("Here I am");
    		session.setAttribute("emailAttribute", Studentmod.getEmail());
    		session.setAttribute("passwordAttribute", Studentmod.getPassword());
    		
    		
    		RequestDispatcher rd = request.getRequestDispatcher("sessionManage");
    		rd.forward(request, response);
        }
        else {
        	response.sendRedirect("./index.html");
        	System.out.println("Wrong Password");
        }    		
        
		PrintWriter out = response.getWriter();
		out.println("The email is " + Studentmod);
	
		   		
	}
}
