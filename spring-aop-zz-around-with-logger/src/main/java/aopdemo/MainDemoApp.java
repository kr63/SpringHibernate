package aopdemo;

import aopdemo.dao.AccountDAO;
import aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainDemoApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        Account account = new Account();
        account.setName("Madhu");
        account.setLevel("Platinum");
        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        accountDAO.setName("foobar");
        accountDAO.setServiceCode("silver");

        String name = accountDAO.getName();
        String code = accountDAO.getServiceCode();

        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
        membershipDAO.addSillyMember();
        membershipDAO.gotoSleep();

        context.close();
    }
}
