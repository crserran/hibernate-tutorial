package cursospring.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cursospring.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int studentId = 7;
			
			// start a transaction 
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("Getting the Student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			// delete the student
			System.out.println("Deleting myStudent: " + studentId);
			session.delete(myStudent);
			
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
