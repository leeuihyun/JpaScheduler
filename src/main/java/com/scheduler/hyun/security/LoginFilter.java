package com.scheduler.hyun.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/api/user/create", "/api/user/login",
        "/api/user/search", "/api/schedule/search"};

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {
                throw new RuntimeException("로그인 해주세요.");
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
