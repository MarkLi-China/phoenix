package com.domain.java.controller;

import com.domain.java.annotation.LoginRequired;
import com.domain.java.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-13
 */
@Controller
@RequestMapping(value = "/default")
public class DefaultController extends BaseController {

    @Autowired
    LoginService loginService;

    @LoginRequired
    public String indexPage(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

        System.out.println(this.getClass().getClassLoader());

        loginService.login("1", "2", "3");
        /*loginService.operate();
        loginService.operate(1);
        loginService.handle();
        loginService.logout();*/

        return AUTO_PAGE;
    }

    public String templateSearchTilesPage(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return AUTO_PAGE;
    }
}
