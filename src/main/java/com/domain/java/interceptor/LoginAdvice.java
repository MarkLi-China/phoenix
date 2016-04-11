package com.domain.java.interceptor;

import com.domain.java.annotation.LoginRequired;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * com.domain.java.interceptor
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/28
 */
@Aspect
@Component
public class LoginAdvice {

    /**
     * 对注解@LoginRequired进行切入
     */
    @Pointcut("@annotation(com.domain.java.annotation.LoginRequired)")
    private void aspectjLogin() {

    }

    @Before(value = "aspectjLogin()")
    public void loginRequiredAdvice(JoinPoint point) {

        System.out.println("-----LoginAdvice.loginRequiredAdvice()-----");
        System.out.println("判断是否已登录，如未登录，跳转到登录页");
        System.out.println("-----End of LoginAdvice.loginRequiredAdvice()------");
    }

    /**
     * 对所有controller进行切入
     * AbstractController中自己实现了代理，切入不了
     */
    @Pointcut("execution(* com.domain.java.controller.*.*(..))")
    private void aspectjController() {

    }

    @Before(value = "aspectjController()")
    public void controllerAdvice(JoinPoint point) {

        System.out.println("-----LoginAdvice.loginRequiredAdvice()-----");
        MethodSignature signature = (MethodSignature) point.getSignature();
        if (signature.getMethod().isAnnotationPresent(LoginRequired.class)) {
            System.out.println("判断是否已登录，如未登录，跳转到登录页");
        }
        System.out.println("-----End of LoginAdvice.loginRequiredAdvice()------");
    }
}
