package spring.demo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach bean = context.getBean("tennisCoach", Coach.class);
        System.out.println(bean.getDailyWorkout());
        System.out.println(bean.getDailyFortune());
        context.close();

        ClassPathXmlApplicationContext readContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach readBean = readContext.getBean("readCoach", Coach.class);
        System.out.println(readBean.getDailyWorkout());
        System.out.println(readBean.getDailyFortune());
        readContext.close();
    }
}
