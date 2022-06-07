package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.CurrencyCode;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.service.account.AccountService;
import com.shlee7131.financedemo.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceImplTest {
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    Account account;
    User user;

    @BeforeEach
    void beforeEach(){
        account = new Account();
        user = new User("shlee7131@gmail.com","asdf");
        userService.createUser(user);
    }

    @Test
    void 계정_생성(){
        account.setCurrencyCode(CurrencyCode.KRW);
        Account account = accountService.createAccount(user, this.account);
        assertThat(account.getCurrencyCode()).isEqualTo(this.account.getCurrencyCode());
    }

    @Test
    void 계정_다중_생성(){
        for(int i = 0 ; i < 3 ; i++) {
            Account temp = new Account();
            temp.setCurrencyCode(CurrencyCode.KRW);
            accountService.createAccount(user, temp);
        }
        Page<Account> accounts = accountService.readByUserWithPage(user, null);
        assertThat(accounts.getContent().size()).isEqualTo(3);
    }

    @Test
    void 계정_삭제(){
        account.setCurrencyCode(CurrencyCode.KRW);
        Account account = accountService.createAccount(user, this.account);

        Long id = account.getId();
        accountService.deleteAccount(id);
        Page<Account> accounts = accountService.readByUserWithPage(user, null);

        assertThat(accounts.getContent().size()).isEqualTo(0);
    }

    @Test
    void 계정_삭제_유효성_실패_id(){
        account.setCurrencyCode(CurrencyCode.KRW);
        Account account = accountService.createAccount(user, this.account);

        assertThrowsExactly(ResourceNotFoundException.class, () ->{
            Long id = account.getId();
            accountService.deleteAccount(++id);
        },"해당 계좌를 찾을 수 없습니다");
    }

    @Test
    void 계정_목록(){
        for(int i = 0 ; i < 3 ; i++) {
            Account temp = new Account();
            temp.setCurrencyCode(CurrencyCode.KRW);
            accountService.createAccount(user, temp);
        }

        Page<Account> accounts = accountService.readByUserWithPage(user, null);
        assertThat(accounts.getContent().size()).isEqualTo(3);
    }
}