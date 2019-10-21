package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("Duffy", "Duck", "duffy@test");
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();

            System.out.println("Saved student. Generated id: " + student.getId());
            session = factory.getCurrentSession();
            session.beginTransaction();
            Student newStudent = session.get(Student.class, student.getId());
            System.out.println("Get complete: " + newStudent);
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
