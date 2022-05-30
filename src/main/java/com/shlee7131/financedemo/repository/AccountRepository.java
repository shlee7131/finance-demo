package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository implements DBRepository<Account> {
    private final Map<Integer, Account> store = new HashMap<>();

    @Override
    public <Integer> Account findById(Integer id) {
        return store.get(id);
    }

    @Override
    public <R> List<Account> findAll(R id) {
        return null;
    }

    @Override
    public <R> Account findByKeywords(R r) {
        return null;
    }

    @Override
    public Account save(Account account) {
        store.put(account.getId(), account);
        return store.get(account.getId());
    }

    @Override
    public <R> void deleteById(R id) {

    }
}
