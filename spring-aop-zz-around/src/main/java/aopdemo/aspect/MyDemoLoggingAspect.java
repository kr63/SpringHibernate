package aopdemo.aspect;

import aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @Around("execution(* aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {

        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println("Duration: " + (end - begin) / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc" )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);
        System.out.println("\n=====>>> The exception is: " + exc);
    }

    @AfterReturning(
            pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n====>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n====>>> result is: " + result);

        convertAccountNamestoUpperCase(result);

        System.out.println("\n====>>> result is: " + result);
    }

    private void convertAccountNamestoUpperCase(List<Account> result) {
        for (Account account : result) {
            String upperName = account.getName().toUpperCase();
            account.setName(upperName);
        }
    }

    @Before("aopdemo.aspect.AopExpressions.forDaoPackageExceptGetAndSet()forDaoPackageExceptGetAndSet()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n====>>> Executing @Before advice on method");

//        get method signature
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method signature: " + methodSig);

//        get args
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }
}

