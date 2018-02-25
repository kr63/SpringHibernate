package spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach bean = context.getBean("thatSillyCoach", Coach.class);

        System.out.println(bean.getDailyWorkout());

        context.close();
    }
}
