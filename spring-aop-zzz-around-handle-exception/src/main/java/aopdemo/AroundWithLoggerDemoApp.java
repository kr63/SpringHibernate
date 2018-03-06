package aopdemo;

import aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

    private static Logger logger =
            Logger.getLogger(AroundWithLoggerDemoApp.class.getName());


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        TrafficFortuneService trafficFortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMain program: AroundDemoApp");
        logger.info("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        logger.info("\nMy fortune is: " + data);
        logger.info("Finished!");

        context.close();
    }
}
