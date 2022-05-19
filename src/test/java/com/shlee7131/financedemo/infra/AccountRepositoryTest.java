package com.shlee7131.financedemo.infra;


import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.entity.AccountEntity;
import com.shlee7131.financedemo.entity.domain.Account;
import com.shlee7131.financedemo.infra.repo.DBRepository;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class AccountRepositoryTest {
    TestConfigContainer tc = TestConfigContainer.getInstance();
    AccountEntity ae =  tc.getBean(AccountEntity.class);

    @Test
    void temp(){
        ae.findById();
    }
}