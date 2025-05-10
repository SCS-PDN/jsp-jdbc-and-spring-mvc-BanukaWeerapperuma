package com.university.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        String user = request.getSession().getAttribute("student") != null
                      ? request.getSession().getAttribute("student").toString()
                      : "Anonymous";

        if (uri.contains("/login") && method.equals("POST")) {
            logger.info("[{}] LOGIN attempt by {}", LocalDateTime.now(), user);
        } else if (uri.contains("/register")) {
            logger.info("[{}] REGISTRATION attempt by {}", LocalDateTime.now(), user);
        }

        return true;
    }
}
