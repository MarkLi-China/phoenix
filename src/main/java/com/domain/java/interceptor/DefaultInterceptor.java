package com.domain.java.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with Intellij IDEA
 * @author QX
 * @version 1.0.0
 * @since 2015-8-26
 */
public class DefaultInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(DefaultInterceptor.class);

    private List<String> exclusionsUrl;

    private long requestStartTime;

    private long requestEndTime;

    private long requestCompleteTime;

    private long timeSpend;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        requestStartTime = System.currentTimeMillis();
        logger.debug("default interceptor pre handle, requestStartTime = " + requestStartTime);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        requestEndTime = System.currentTimeMillis();
        timeSpend = requestEndTime - requestStartTime;
        logger.debug("default interceptor post handle, requestEndTime = " + requestEndTime + ", spend " + timeSpend);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        requestCompleteTime = System.currentTimeMillis();
        timeSpend = requestCompleteTime - requestStartTime;
        logger.debug("default interceptor after completion, requestCompleteTime = " + requestCompleteTime + ", spend " + timeSpend);
    }

    public void setExclusionsUrl(List<String> exclusionsUrl) {

        this.exclusionsUrl = exclusionsUrl;
    }

    public List<String> getExclusionsUrl() {

        return exclusionsUrl;
    }
}
