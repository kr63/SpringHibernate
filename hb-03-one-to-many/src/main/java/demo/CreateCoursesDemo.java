package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Course course1 = new Course("Course 1");
            Course course2 = new Course("Course 2");
            Course course3 = new Course("Course 3");

            session.beginTransaction();

            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            tempInstructor.add(course1);
            tempInstructor.add(course2);
            tempInstructor.add(course3);

            session.save(course1);
            session.save(course2);
            session.save(course3);

            session.getTransaction().commit();

            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
