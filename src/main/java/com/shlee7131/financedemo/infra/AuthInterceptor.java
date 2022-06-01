package com.shlee7131.financedemo.infra;


import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AuthService authService;

    @Override
    // RuntimeException 추가
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();

        if (cookies == null ) throw new BadRequestException("로그인 해주세요");

        Optional<Cookie> session = Arrays
                .stream(cookies)
                .filter(cookie -> cookie.getName().equals("session"))
                .findFirst();

        if (session.isEmpty()) {
            log.error("Login Error: {}",request);
            throw new BadRequestException("로그인 해주세요");
        } else {
            log.info("Logged in: {}", session.get());
            return true;
        }
    }
}
