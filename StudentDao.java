package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.StudentModel;


public class StudentDao {
	
	public StudentModel registerStudent(StudentModel studObj) {		
		try {
			
			Session session = HibernateUtil.getSession().openSession();			
			session.save(studObj);
			
			session.beginTransaction().commit();		
			session.close();
			return studObj;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public void updateStudent(StudentModel studObj) {
		Transaction transaction = null;
		
		try {
			
			Session session = HibernateUtil.getSession().openSession();
			transaction = session.beginTransaction();
			
			session.update(studObj);
			
			transaction.commit();
			
			session.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}	
	
	public void deleteStudent(StudentModel studObj) {
		Transaction transaction = null;
		
		try {
			
			Session session = HibernateUtil.getSession().openSession();
			transaction = session.beginTransaction();
			
			session.delete(studObj);
			
			transaction.commit();
			
			session.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}	
	
	   public List<StudentModel> findAll() {
	        List<StudentModel> studentList = new ArrayList<>();
	        try{
	        	
	        	Session session = HibernateUtil.getSession().openSession();
	            Transaction transaction = session.beginTransaction();

	            Query<StudentModel> query = session.createQuery("FROM studentModel", StudentModel.class);
	            studentList = query.list();

	            transaction.commit();
	        } catch (Exception e) {
	         
	            handleException("Failed to retrieve all Students", e);
	        }
	        return studentList;
	    }

	    public StudentModel findByStudentEmail(String StudentEmail) {
	        StudentModel Student = null;
	        try{
	        	
	        	Session session = HibernateUtil.getSession().openSession();
	            Transaction transaction = session.beginTransaction();
	            Query<StudentModel> query = session.createQuery("FROM studentModel WHERE email = :email", StudentModel.class);
	            query.setParameter("email", StudentEmail);
	            Student = query.uniqueResult();

	            transaction.commit();
	        } catch (Exception e) {
	            handleException("Failed to retrieve student by email", e);
	        }
	        return Student;
	    }
	        

	     public boolean updatePassword(String StudentEmail, String newPassword) {
	         boolean answer = false;
	        try{
	        	
	        	Session session = HibernateUtil.getSession().openSession();
	            Transaction transaction = session.beginTransaction();

	            // Retrieve the Student by email
	            StudentModel Student = findByStudentEmail(StudentEmail);

	            if (Student != null) {
	               
	                Student.updatePassword(newPassword);

	                // Save the updated password
	                session.update(Student);

	                transaction.commit();
	                answer = true;
	                return answer;
	                       
	            } else {
	             
	                System.out.println("student not found with email: " + StudentEmail);
	                 return answer;
	            }
	        } catch (Exception e) {
	            handleException("Failed to update password", e);
	        }
	        return answer;
	    }
	    
	    private void handleException(String message, Exception e) {
	        
	        System.out.println(message);
	       
	        e.printStackTrace(); 
	       
	    }	
	
}
