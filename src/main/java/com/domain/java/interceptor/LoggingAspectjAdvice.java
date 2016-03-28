package com.domain.java.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * com.domain.java.interceptor
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/23
 */
@Aspect
@Component
public class LoggingAspectjAdvice {

    /**
     * Pointcut
     * 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
     * 该方法就是一个标识，不进行调用
     */
    @Pointcut("execution(* com.domain.java.service.impl.LoginServiceImpl.logout(..))")
    private void aspectjMethod() {

    }

    /**
     * 在核心业务执行前执行，不能阻止核心业务的调用。
     * @param point
     */
    @Before(value = "aspectjMethod()")
    public void beforeAdvice(JoinPoint point) {

        System.out.println("-----LoggingAspectjAdvice.beforeAdvice().invoke-----");
        System.out.println("此处意在执行核心业务逻辑前，做一些安全性的判断等等");
        System.out.println("可通过point来获取所需要的内容");
        System.out.println("-----End of LoggingAspectjAdvice.beforeAdvice()------");
    }

    /**
     * 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
     * @param point
     */
    @After(value = "aspectjMethod()")
    public void afterAdvice(JoinPoint point) {

        System.out.println("-----LoggingAspectjAdvice.afterAdvice().invoke-----");
        System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
        System.out.println(" 可通过point来获取所需要的内容");
        System.out.println("-----End of LoggingAspectjAdvice.afterAdvice()------");
    }

    /**
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理
     * 注意：当核心业务抛异常后，立即退出，转向After Advice
     * 执行完毕After Advice，再转到Throwing Advice
     * @param point
     * @return
     * @throws Throwable
     */
    @Around(value = "aspectjMethod()")
    public Object aroundAdvice(ProceedingJoinPoint point) throws Throwable {

        System.out.println("-----LoggingAspectjAdvice.aroundAdvice().invoke-----");
        System.out.println(" 此处可以做类似于Before Advice的事情");

        //调用核心逻辑
        Object retVal = point.proceed();

        System.out.println(" 此处可以做类似于After Advice的事情");
        System.out.println("-----End of LoggingAspectjAdvice.aroundAdvice()------");
        return retVal;
    }

    /**
     * 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
     * @param point
     * @param returnObj
     */
    @AfterReturning(value = "aspectjMethod()", returning = "returnObj")
    public void returnAdvice(JoinPoint point, Object returnObj) {

        System.out.println("-----LoggingAspectjAdvice.returnAdvice().invoke-----");
        System.out.println("Return Value: " + returnObj);
        System.out.println(" 此处可以对返回值做进一步处理");
        System.out.println(" 可通过point来获取所需要的内容");
        System.out.println("-----End of LoggingAspectjAdvice.returnAdvice()------");
    }

    /**
     * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
     * @param point
     * @param ex
     */
    @AfterThrowing(value = "aspectjMethod()", throwing = "ex")
    public void throwingAdvice(JoinPoint point, Throwable ex) {

        System.out.println("-----LoggingAspectjAdvice.throwingAdvice().invoke-----");
        System.out.println(" 错误信息：" + ex.getMessage());
        System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
        System.out.println(" 可通过point来获取所需要的内容");
        System.out.println("-----End of LoggingAspectjAdvice.throwingAdvice()------");
    }
}
