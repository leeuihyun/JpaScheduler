package com.scheduler.hyun.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.hyun.domain.dto.common.ErrorResponse;
import com.scheduler.hyun.enums.ErrorEnum;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@RequiredArgsConstructor
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/api/user/create", "/api/user/login",
        "/api/user/search/**", "/api/schedule/search/**", "/api/comment/search/**"};
    private final ObjectMapper objectMapper;

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (!isWhiteList(requestURI)) {

            HttpSession session = httpRequest.getSession(false);
            if (session == null || session.getAttribute("userId") == null) {

                ErrorResponse errorResponse = new ErrorResponse(
                    ErrorEnum.NO_AUTHORIZATION.getHttpStatus(),
                    ErrorEnum.NO_AUTHORIZATION.getErrorMessage());

                httpResponse.setContentType("application/json;charset=UTF-8");
                httpResponse.getWriter().write(objectMapper.writeValueAsString(errorResponse));

                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
