package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            int studentId = 1;

            session.beginTransaction();
            System.out.println("\nGetting student with id: " + studentId);
            Student student = session.get(Student.class, studentId);

            System.out.println("Updating student...");
            student.setFirstName("Scooby");

            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("\nUpdate email for all students");

            session.createQuery("update Student set email='foo@test'").executeUpdate();

            session.getTransaction().commit();




            System.out.println("Done!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
