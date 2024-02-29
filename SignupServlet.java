package servletPack;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import model.StudentModel;

/**
 * Servlet implementation class SignupServlet
 */

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		
		
		StudentModel data = (StudentModel) request.getAttribute("Student_data");
		StudentDao dao = new StudentDao();
		dao.registerStudent(data);
		
		System.out.println("Before redirecting");
		System.out.println("\n The Studentmod is " + data.getName());
		
		
		// send email to student 
		
		try {
			sendEmail(data.getEmail(), data.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		System.out.print("WELCOME HERE");
		
		
		response.getWriter().println("<script>window.location='./index.html';</script>");
		
		doGet(request, response);
	}

	
	public void sendEmail(String StudentEmail, String fullname) throws Exception {
	  Properties properties = new Properties();
	  properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
	  properties.put("mail.smtp.host", "smtp.gmail.com");
	  properties.put("mail.smtp.port", "587");
	  properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
	  
	  final String  myEmail ="tuyisengenelyse@gmail.com";
	  final String password = "mvcamhjhincqeaevs";
         
  
	  Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
	      @Override
	      protected PasswordAuthentication getPasswordAuthentication(){
	          return new PasswordAuthentication(myEmail,password);
	      }
	  });
	  try {
	          Message message = new MimeMessage(session);
	          message.setRecipient(Message.RecipientType.TO, new InternetAddress(StudentEmail));
	          message.setSubject("Confirmation message");
	          String messageBody = "Dear " + fullname + " " + ",\n\nThank you for registering yourself here. We have received your information and we're working on it.";
	          message.setText(messageBody);
	          Transport.send(message);
	          System.out.println("Message sent successfully.");
	  } catch (MessagingException e) {
	          System.out.println("Error sending message: " + e.getMessage());
      }	
	  
	}	
}
