package com.shlee7131.financedemo.service.account;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;


    @Override
    public Page<Account> readByUserWithPage(User user, Pageable pageable) {
        Page<Account> allByUserWithPage = accountRepository.findAllByUser(user, pageable);
        log.info("AccountServiceImpl::readByUserWithPage: {}", allByUserWithPage);
        return  allByUserWithPage;
    }

    @Override
    public Optional<Account> readById(long id) {
        Optional<Account> byId = accountRepository.findById(id);
        return byId;
    }

    @Override
    public Account createAccount(User user, Account account) {
        // account 생성은 시스템에서 처리한다
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(long id) {
        Optional<Account> byId = accountRepository.findById(id);
        if (byId.isEmpty()) throw new ResourceNotFoundException("해당 계좌를 찾을 수 없습니다");
        accountRepository.deleteById(id);
    }
}
