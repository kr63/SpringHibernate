package hibernate.demo;

import hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            Student student1 = new Student("John", "Dou", "john@test");
            Student student2 = new Student("Mary", "Public", "mary@test");
            Student student3 = new Student("Bonita", "Applebum", "bonita@test");
            session.beginTransaction();
            session.save(student1);
            session.save(student2);
            session.save(student3);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }
    }
}
