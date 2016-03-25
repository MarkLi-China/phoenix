package com.domain.java.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * com.domain.java.interceptor
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/25
 */
@Component("loggingInterceptor")
public class LoggingInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object ret;
        try {
            System.out.println(" 此处可以做类似于Before Advice的事情");

            System.out.println("method " + methodInvocation.getMethod() + " is called on " +
                    methodInvocation.getThis() + " with args " + Arrays.toString(methodInvocation.getArguments()));
            ret = methodInvocation.proceed();
            System.out.println("method " + methodInvocation.getMethod() + " returns " + ret);

            System.out.println(" 此处可以做类似于After Advice的事情");
        } catch (Throwable throwable) {
            System.out.println(" 此处可以做类似于After Throwing的事情？？");
            throw throwable;
        }
        System.out.println(" 此处可以做类似于After Returning的事情？？");

        return ret;
    }
}
