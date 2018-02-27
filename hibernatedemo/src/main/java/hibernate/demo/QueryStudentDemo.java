package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Student> students = session.createQuery("from Student").getResultList();
            displayStudents(students);

            students = session.createQuery("from Student s where s.lastName='Dou'").list();
            System.out.println("\nStudents who have last name of Dou");
            displayStudents(students);

            students = session.createQuery("from Student s where " +
                    "s.lastName='Dou' or s.firstName='Duffy'").getResultList();
            System.out.println("\nStudents who have last name of Dou or first name of Duffy");
            displayStudents(students);

            students = session.createQuery("from Student s where " +
                    "s.email Like '%test'").list();
            System.out.println("\nStudents who have email ends with test");
            displayStudents(students);

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> students) {
        for (Student tempStudent : students) {
            System.out.println(tempStudent);
        }
    }
}
