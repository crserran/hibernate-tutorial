package cursospring.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cursospring.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
	
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction 
			session.beginTransaction();
			
			// query students
			List<Student> students = session.createQuery("from Student").list();
			
			// display the students
			for(Student student : students) {
				System.out.println(student);
			}
			
			// query students lastName=Doe
			students = session.createQuery("from Student s where s.lastName='Doe'").list();	
			
			// display the students
			System.out.println("\n\nStudents who has last name Doe");
			for(Student student : students) {
				System.out.println(student);
			}
						
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
