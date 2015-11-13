package com.domain.java.controller;

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

    public String indexPage(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {

        System.out.println(this.getClass().getClassLoader());
        return AUTO_PAGE;
    }

    public String templateSearchTilesPage(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return AUTO_PAGE;
    }
}
