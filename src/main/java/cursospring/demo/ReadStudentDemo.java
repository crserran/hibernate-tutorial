package cursospring.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cursospring.demo.entity.Student;

public class ReadStudentDemo {

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
			Student theStudent = new Student("", "", "benito@sogeti.com");
			
			// start a transaction 
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the Student...");
			System.out.println(theStudent);
			session.save(theStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			// print student id
			System.out.println("Generated student id: " + theStudent.getId());
			
			// start a new session
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve the student
			System.out.println("\nGetting student with id: " + theStudent.getId());
			Student myStudent = session.get(Student.class, theStudent.getId());
			System.out.println("Get completed: " + myStudent);
			
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
