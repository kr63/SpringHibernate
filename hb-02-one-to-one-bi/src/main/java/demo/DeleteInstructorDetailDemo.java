package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            int theId = 3;
            InstructorDetail detail = session.get(InstructorDetail.class, theId);

            System.out.println("Instructor detail: " + detail);
            System.out.println("the associated instructor: " + detail.getInstructor());

            System.out.println("Deleting instructor detail: " + detail);
            detail.getInstructor().setInstructorDetail(null);
            session.delete(detail);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
