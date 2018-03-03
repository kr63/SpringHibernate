package aopdemo;

import aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        accountDAO.addAccount();
        accountDAO.addAccount();

        context.close();
    }
}
