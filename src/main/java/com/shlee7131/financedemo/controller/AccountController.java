package com.shlee7131.financedemo.controller;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.service.account.AccountAdapter;
import com.shlee7131.financedemo.service.dto.AccountReqDto;
import com.shlee7131.financedemo.service.dto.AccountRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountAdapter accountAdapter;

    @GetMapping("/accounts")
    public ResponseEntity<Page<AccountRespDto>> getAccountsWithPage(Pageable pageable, HttpServletRequest httpServletRequest) {
        Optional<Cookie> session = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals("session")).findFirst();
        log.error("getAccountsWithPage: {}", session.get());
        if (session.isEmpty()) throw new BadRequestException("로그인이 필요합니다");
        Page<AccountRespDto> accountRespDtoPage = accountAdapter.readAccountsWithPage(session.get().getValue(), pageable);
        return new ResponseEntity<>(accountRespDtoPage, HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public ResponseEntity<AccountRespDto> createAccount(@RequestBody AccountReqDto accountReqDto , HttpServletRequest httpServletRequest) {
        Optional<Cookie> session = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals("session")).findFirst();
        if (session.isEmpty()) throw new BadRequestException("로그인이 필요합니다");
        AccountRespDto accountRespDto = accountAdapter.createAccount(session.get().getValue(), accountReqDto);

        return new ResponseEntity<>(accountRespDto, HttpStatus.CREATED);
    }
}
