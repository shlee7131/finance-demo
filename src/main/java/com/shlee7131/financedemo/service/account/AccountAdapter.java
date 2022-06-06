package com.shlee7131.financedemo.service.account;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.dto.AccountReqDto;
import com.shlee7131.financedemo.service.dto.AccountRespDto;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountAdapter {
    AccountRespDto createAccount(String sessionId, AccountReqDto accountReqDto);
    Page<AccountRespDto> readAccountsWithPage(String sessionId, Pageable pageable);
    void deleteAccount(long id);
}
