package com.shlee7131.financedemo.service.account;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Page<Account> readByUserWithPage(User user, Pageable pageable);
    Optional<Account> readById(long id);
    Account createAccount(User user, Account account);
    void deleteAccount(long id);
}
