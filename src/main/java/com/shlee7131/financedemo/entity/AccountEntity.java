package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.entity.domain.Account;
import com.shlee7131.financedemo.repository.DBRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountEntity {
    private final DBRepository<Account> repo;

    public Account findById(int id) {
        return repo.findById(id);
    }

    public Account save(Account account){
        return repo.save(account);
    }
}
