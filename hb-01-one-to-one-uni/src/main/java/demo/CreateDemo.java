package demo;

import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Instructor instructor = new Instructor(
                    "Chad",
                    "Darby",
                    "chad@test");
            InstructorDetail detail = new InstructorDetail(
                    "http://www.test.com",
                    "Luv 2 code!!");

            session.beginTransaction();

            instructor.setInstructorDetail(detail);
            session.save(instructor);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
