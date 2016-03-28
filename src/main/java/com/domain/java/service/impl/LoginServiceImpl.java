package com.domain.java.service.impl;

import com.domain.java.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * com.domain.java.service.impl
 * @author Mark Li
 * @version 1.0.0
 * @since 2016/3/23
 */
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(String userName, String password, String verifyCode) {

        System.out.println("userName = [" + userName + "], password = [" + password + "], verifyCode = [" + verifyCode + "]");

        if (StringUtils.isBlank(verifyCode)) {
            throw new IllegalArgumentException("验证码为空");
        }

        return StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password);
    }

    @Override
    public void logout() {

        System.out.println("logout......");
    }

    @Override
    public void operate() {

        System.out.println("operate......");
    }

    @Override
    public void operate(Integer operatorId) {

        System.out.println(operatorId + " operate......");
    }

    @Override
    public Object handle(String... args) {

        System.out.println("LoginServiceImpl.handle");
        System.out.println("args = [" + Arrays.toString(args) + "]");
        return null;
    }
}
