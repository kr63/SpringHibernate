package demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesAndReviewsDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                .configure("hibernate.cfg.xml")
                                .addAnnotatedClass(Instructor.class)
                                .addAnnotatedClass(InstructorDetail.class)
                                .addAnnotatedClass(Course.class)
                                .addAnnotatedClass(Review.class)
                                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Course course = new Course("Course 1");
            course.addReview(new Review("Comment 1"));
            course.addReview(new Review("Comment 2"));
            course.addReview(new Review("Comment 3"));

            session.beginTransaction();

            System.out.println(course);
            System.out.println(course.getReviews());
            session.save(course);

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
