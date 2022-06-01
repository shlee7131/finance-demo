package com.shlee7131.financedemo.controller;

import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.service.AuthService;
import com.shlee7131.financedemo.service.UserService;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/auth/local")
    public ResponseEntity<?> login(@Valid @RequestBody UserReqDto userReqDto, HttpServletResponse response) {
        Optional<String> sessionId = authService.login(userReqDto);

        if (sessionId.isEmpty()) throw new BadRequestException("아이디 또는 비밀번호가 일치하지 않습니다");

        Cookie cookie = new Cookie("session", sessionId.get());
        // expires in 7 days
        cookie.setMaxAge(7 * 24 * 60 * 60);

        response.addCookie(cookie);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/auth/local/new")
    public ResponseEntity<UserRespDto> register(@Valid @RequestBody UserReqDto userReqDto) {
        Optional<UserRespDto> userRespDto = userService.createUser(userReqDto);
        log.info("userReqDto: {}",userReqDto.toString());
        log.info("userRespDto: {}", userRespDto.toString());

        if (userRespDto.isEmpty()) throw new BadRequestException("이메일이 중복됩니다");

        return new ResponseEntity<>(userRespDto.get(), HttpStatus.CREATED);
    }
}
