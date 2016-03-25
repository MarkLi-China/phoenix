package com.domain.java.service;

import com.domain.java.base.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * com.domain.java.service
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/23
 */
public class LoginServiceTest extends BaseTest {

    @Autowired
    LoginService loginService;

    @Test
    public void testLogin() {
        loginService.login("xxx", "yyy", "zzz");
//        loginService.login("xxx", "yyy", "");
    }

    @Test
    public void testLogout() {
        loginService.logout();
    }

    @Test
    public void testOperate() {
        loginService.operate();
    }
}
