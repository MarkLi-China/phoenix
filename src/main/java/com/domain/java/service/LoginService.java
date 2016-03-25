package com.domain.java.service;

/**
 * com.domain.java.service
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/23
 */
public interface LoginService {

    boolean login(String userName, String password, String verifyCode);

    void logout();

    void operate();
}
