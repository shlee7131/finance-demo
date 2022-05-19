package com.shlee7131.financedemo.infra;

import com.shlee7131.financedemo.entity.domain.Account;
import com.shlee7131.financedemo.infra.repo.DBRepository;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepository implements DBRepository<Account> {
    @Override
    public Account findById() {
        return new Account(1,1,1,"name");
    }
}
