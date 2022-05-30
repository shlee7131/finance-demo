package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.entity.domain.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountEntityTest {
    TestConfigContainer tc = TestConfigContainer.getInstance();
    AccountEntity ae = tc.getBean(AccountEntity.class);

    @Test
    @DisplayName("계좌 번호로 찾기")
    void findById(){
        Account account = new Account(1,1,1,"temp");
        Account save = ae.save(account);
        Account byId = ae.findById(1);
        Assertions.assertEquals(save ,byId);
    }

    @Test
    @DisplayName("계좌 생성")
    void save(){
        Account account = new Account(1,1,1,"temp");
        Account save = ae.save(account);
        Assertions.assertEquals(account, save);
    }

}