package in.demo.main;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.demo.model.Courses;
import in.demo.util.HibernateUtil;

public class UpdateApp{
	public static void main(String[] args) throws Exception {
		Session session=null;
		Transaction transaction =null;
		boolean executed=false;
		Scanner sc=new Scanner(System.in);
		
		
		try {
			session = HibernateUtil.getSession();
			if(session !=null)
				transaction = session.beginTransaction();
			if(transaction!=null) {
				Courses courses = new Courses();
				System.out.print("Enter course ID:: ");
				courses.setID(sc.nextInt());
				System.out.print("Enter course name:: ");
				String courseName =  sc.next();
				courses.setCourse(courseName);
				System.out.print("Enter course fees:: ");
				courses.setFees(sc.nextDouble());
				session.saveOrUpdate(courses);
				executed=true;
				}
			}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(executed) {
				transaction.commit();
				List resultList = session.createQuery("SELECT a FROM Courses a",Courses.class).getResultList();
				if(resultList!=null)
					resultList.forEach(System.out::println);
				else
					System.out.println("no records in the table");
				System.out.println("***Transcation commited to database****");
			}
			else {
				transaction.rollback();
				System.out.println("Object not updated to database...");
			}
		}
		
		HibernateUtil.closeSession(session);
		HibernateUtil.closeSessionFactory();
	
	}
}