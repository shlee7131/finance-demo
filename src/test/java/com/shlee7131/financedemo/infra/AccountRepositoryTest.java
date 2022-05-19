package com.shlee7131.financedemo.infra;


import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.entity.AccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


class AccountRepositoryTest {
    TestConfigContainer tc = new TestConfigContainer();
    AccountEntity ae =  tc.getBean(AccountEntity.class);

    @Test
    void temp(){
        ae.findById();
    }
}