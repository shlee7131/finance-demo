package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.repository.AccountRepository;
import com.shlee7131.financedemo.repository.UserRepository;
import com.shlee7131.financedemo.service.dto.AccountReqDto;
import com.shlee7131.financedemo.service.dto.AccountRespDto;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public Optional<Account> createAccount(AuthInfoDto authInfoDto, AccountReqDto accountReqDto) {
        Optional<User> byId = userRepository.findById(authInfoDto.getId());

        if (byId.isEmpty()) Optional.empty();

        User user = byId.get();

        Account account = new Account();
        account.setCurrency_code(accountReqDto.getCurrencyCode());

        user.addAccount(account);

        return Optional.ofNullable(account);
    }

    public Optional<Account> readAccount(AuthInfoDto authInfoDto, Long id){
        Optional<User> byId = userRepository.findById(authInfoDto.getId());
        if (byId.isEmpty()) Optional.empty();

        return byId.get()
                .getAccounts()
                .stream()
                .filter(account -> account.getId().equals(id))
                .findFirst();
    }

    public List<Account> readAccountAll(AuthInfoDto authInfoDto) {
        Optional<User> byId = userRepository.findById(authInfoDto.getId());
        if (byId.isEmpty()) return null;
        return byId.get().getAccounts();
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
}
