package aopdemo.aspect;

import aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {

        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        logger.info("Duration: " + (end - begin) / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>> Executing @After (finally) on method: " + method);
    }

    @AfterThrowing(
            pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exc" )
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>> Executing @AfterThrowing on method: " + method);
        logger.info("\n=====>>> The exception is: " + exc);
    }

    @AfterReturning(
            pointcut = "execution(* aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result) {

        String method = joinPoint.getSignature().toShortString();
        logger.info("\n====>>> Executing @AfterReturning on method: " + method);

        logger.info("\n====>>> result is: " + result);

        convertAccountNamestoUpperCase(result);

        logger.info("\n====>>> result is: " + result);
    }

    private void convertAccountNamestoUpperCase(List<Account> result) {
        for (Account account : result) {
            String upperName = account.getName().toUpperCase();
            account.setName(upperName);
        }
    }

    @Before("aopdemo.aspect.AopExpressions.forDaoPackageExceptGetAndSet()forDaoPackageExceptGetAndSet()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        logger.info("\n====>>> Executing @Before advice on method");

//        get method signature
        MethodSignature methodSig = (MethodSignature) joinPoint.getSignature();
        logger.info("Method signature: " + methodSig);

//        get args
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info(arg.toString());

            if (arg instanceof Account) {
                Account account = (Account) arg;
                logger.info("Account name: " + account.getName());
                logger.info("Account level: " + account.getLevel());
            }
        }
    }
}

