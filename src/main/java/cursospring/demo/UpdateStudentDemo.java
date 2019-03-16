package cursospring.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cursospring.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 6;
			
			// start a transaction 
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("Getting the Student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");
			myStudent.setFirstName("Scooby");
			
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
