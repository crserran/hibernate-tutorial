package cursospring.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cursospring.demo.entity.Student;

public class CreateStudentDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			// create a student object
			System.out.println("Creating a student object");
			Student theStudent = new Student("John", "Doe", "johndoe@sogeti.com");
			
			// start a transaction 
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the Student...");
			session.save(theStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
		}
	}
	
}
