package aopdemo;

import aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        List<Account> accounts = null;

        try {
            accounts = accountDAO.findAccounts(true);
        } catch (Exception exc) {
            System.out.println("\n\nMain program ... caught exception: " + exc);
        }

        System.out.println("\n\nMain program: AfterThrowingDemoApp");
        System.out.println("-------");
        System.out.println(accounts);

        context.close();
    }
}
