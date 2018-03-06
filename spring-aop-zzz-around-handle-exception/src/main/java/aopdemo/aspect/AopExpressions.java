package aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExpressions {

    @Pointcut("execution(* aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("execution(* aopdemo.dao.*.get*(..))")
    public void getter() {
    }

    @Pointcut("execution(* aopdemo.dao.*.set*(..))")
    public void setter() {
    }

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageExceptGetAndSet() {
    }
}
