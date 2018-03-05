package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    @Before("execution(public void aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice() {
        System.out.println("\n====>>> Executing @Before advice on addAccount()");
    }

    @Before("execution(public void add*())")
    public void beforeAddAccountAdviceAll() {
        System.out.println("\n====>>> Executing @Before advice on all add methods");
    }

    @Before("execution(* add*())")
    public void beforeAddAccountAdviceAllReturnType() {
        System.out.println("\n====>>> Executing @Before advice on all add methods any return type");
    }

    @Before("execution(* add*(aopdemo.Account, ..))")
    public void beforeAddAccountAdviceAccount() {
        System.out.println("\n====>>> Executing @Before advice on add methods and Account arg");
    }

    @Before("execution(* add*(..))")
    public void beforeAdd() {
        System.out.println("\n====>>> Executing @Before advice on add methods with any args");
    }

    @Before("execution(* aopdemo.dao.*.*(..))")
    public void beforeMethodinPackage() {
        System.out.println("\n====>>> Executing @Before advice on all methods in package");
    }
}
