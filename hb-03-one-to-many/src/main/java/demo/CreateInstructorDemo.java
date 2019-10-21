package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor(
                    "Susan",
                    "Public",
                    "susan@test");
            InstructorDetail tempInstructorDetail = new InstructorDetail(
                    "http://www.test.com",
                    "Video Games");
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();

            session.save(tempInstructor);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
