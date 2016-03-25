package com.domain.java.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.domain.java.annotation
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceCache {

    int value() default -1;

    int expire() default 60;

    ServiceCache.Key key() default Key.JSON;

    String[] includeKeys() default {};

    boolean sync() default false;

    boolean nullPattern() default true;

    public static enum Key {
        JSON,
        TO_STRING;

        private Key() {

        }
    }
}
