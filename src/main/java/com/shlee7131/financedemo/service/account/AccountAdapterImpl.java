package com.shlee7131.financedemo.service.account;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.AuthUtils;
import com.shlee7131.financedemo.service.Transformer;
import com.shlee7131.financedemo.service.dto.AccountReqDto;
import com.shlee7131.financedemo.service.dto.AccountRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Slf4j
@Component
@RequiredArgsConstructor
public class AccountAdapterImpl implements AccountAdapter {
    private final Transformer transformer;
    private final AccountService accountService;
    private final AuthUtils authUtils;

    @Override
    public AccountRespDto createAccount(String sessionId, AccountReqDto accountReqDto) {
        User loggedInUser = authUtils.getLoggedInUser(sessionId);

        Account account = transformer.transform(accountReqDto, Account.class);
        log.info("AccountAdapterImpl::createAccount::account::currencyCode: {}", account.getCurrencyCode());

        Account saved = accountService.createAccount(loggedInUser, account);

        return transformer.transform(saved, AccountRespDto.class);
    }

    @Override
    public Page<AccountRespDto> readAccountsWithPage(String sessionId, Pageable pageable) {
        User loggedInUser = authUtils.getLoggedInUser(sessionId);

        Page<Account> accounts = accountService.readByUserWithPage(loggedInUser, pageable);
        log.info("AccountAdapterImpl::readAccountsWithPage: {}", accounts);

        Page<AccountRespDto> accountRespDtoPage = accounts.map(new Function<Account, AccountRespDto>() {
            @Override
            public AccountRespDto apply(Account account) {
                return transformer.transform(account, AccountRespDto.class);
            }
        });

        return accountRespDtoPage;
    }

    @Override
    public void deleteAccount(long id) {
        accountService.deleteAccount(id);
    }
}
