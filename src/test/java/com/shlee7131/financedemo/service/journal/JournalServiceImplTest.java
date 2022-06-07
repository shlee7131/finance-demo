package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.entity.enums.CurrencyCode;
import com.shlee7131.financedemo.entity.enums.JournalAccountSign;
import com.shlee7131.financedemo.repository.JournalRepository;
import com.shlee7131.financedemo.service.account.AccountService;
import com.shlee7131.financedemo.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JournalServiceImplTest {
    @Autowired
    JournalService journalService;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    JournalRepository journalRepository;

    Account account;
    User user;
    Journal journal;

    @BeforeEach
    void beforeEach(){
        user = new User("shlee7131@gmail.com", "asdf");
        userService.createUser(user);

        account = new Account();
        account.setCurrencyCode(CurrencyCode.KRW);
        accountService.createAccount(user, account);

        journal = new Journal();
        journal.setAccount(account);
    }

    @Test
    void 원장_생성(){
        Journal save = journalService.createJournal(journal);

        Page<Account> accounts = accountService.readByUserWithPage(user, null);
        Account account = accounts.getContent().get(0);

        assertThat(save.getAccount().getId()).isEqualTo(account.getId());
        assertThat(save.getAccount().getCurrencyCode()).isEqualTo(account.getCurrencyCode());
    }

    @Test
    void 원장_수정() {
        journal.setBriefs("원본");
        journal.setDebitSign(JournalAccountSign.PLUS);
        journal.setCreditSign(JournalAccountSign.MINUS);
        journal.setDebitAmount(1000);
        journal.setCreditAmount(1000);

        Journal save = journalRepository.save(journal);

        Long id = save.getId();
        Journal journal = new Journal();
        journal.setBriefs("수정본");
        journalService.updateJournal(id, journal);

        Journal updated = journalRepository.findById(id).get();

        assertThat(updated.getBriefs()).isEqualTo("수정본");
        assertThat(updated.getDebitAmount()).isEqualTo(1000);
        assertThat(updated.getCreditAmount()).isEqualTo(1000);
        assertThat(updated.getDebitSign()).isEqualTo(JournalAccountSign.PLUS);
        assertThat(updated.getCreditSign()).isEqualTo(JournalAccountSign.MINUS);
    }

    @Test
    void 원장_삭제() {
        Journal save = journalRepository.save(journal);

        Long id = save.getId();
        journalService.deleteJournal(id);

        Optional<Journal> byId = journalRepository.findById(id);
        Page<Account> accounts = accountService.readByUserWithPage(user, null);
        int journalsInAccount = accounts.getContent().get(0).getJournals().size();

        assertThat(byId).isEmpty();
        assertThat(journalsInAccount).isEqualTo(0);
    }

    @Test
    void 원장_목록(){
        for(int i = 0 ; i < 13 ; i++) {
            Journal temp = new Journal();
            temp.setAccount(account);
            journalService.createJournal(temp);
        }

        Page<Journal> all = journalService.readJournalsWithPage(this.account, PageRequest.of(0,20, Sort.unsorted()));
        assertThat(all.getContent().size()).isEqualTo(13);
    }
}