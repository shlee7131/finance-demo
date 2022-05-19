package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.entity.domain.Account;
import com.shlee7131.financedemo.infra.repo.DBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountEntity {
    private final DBRepository<Account> repo;

    public Account findById() {
        System.out.println("hello");
        return repo.findById();
    }
}
