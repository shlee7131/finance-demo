package com.shlee7131.financedemo.infra;


import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.entity.AccountEntity;
import com.shlee7131.financedemo.entity.domain.Account;
import com.shlee7131.financedemo.infra.repo.DBRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

class AccountRepositoryTest {
    TestConfigContainer tc = TestConfigContainer.getInstance();
    DBRepository<Account> repo =  tc.getBean(AccountRepository.class);

    @Test
    @DisplayName("계좌 번호로 찾기")
    void findById(){
        Account account = new Account(1,1,1,"temp");
        Account save = repo.save(account);
        Account byId = repo.findById(1);

        Assertions.assertEquals(save, byId);
    }

    @Test
    @DisplayName("계좌 생성")
    void create(){
        Account account = new Account(1,1,1,"temp");
        Account save = repo.save(account);

        Assertions.assertEquals(account,save);
    }
}