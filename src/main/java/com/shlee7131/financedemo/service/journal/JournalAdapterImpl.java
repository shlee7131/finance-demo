package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.service.Transformer;
import com.shlee7131.financedemo.service.account.AccountService;
import com.shlee7131.financedemo.service.dto.JournalReqDto;
import com.shlee7131.financedemo.service.dto.JournalRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JournalAdapterImpl implements JournalAdapter {
    private final AccountService accountService;
    private final JournalService journalService;
    private final Transformer transformer;

    @Override
    public JournalRespDto deposit(JournalReqDto journalReqDto) {
        return getJournalRespDto(journalReqDto);
    }

    @Override
    public JournalRespDto withdraw(JournalReqDto journalReqDto) {
        return getJournalRespDto(journalReqDto);
    }

    private JournalRespDto getJournalRespDto(JournalReqDto journalReqDto) {
        long accountId = journalReqDto.getAccountId();

        Optional<Account> byId = accountService.readById(accountId);
        if (byId.isEmpty()) throw new ResourceNotFoundException("해당 계좌를 찾을 수 없습니다");

        Journal journal = new Journal();

//        Journal journal = journalService.createJournal(transformer.transform(journalReqDto, Journal.class));

        return transformer.transform(journal, JournalRespDto.class);
    }

    @Override
    public Page<JournalRespDto> readJournalsWithPage(long accountId, Pageable pageable) {
        return null;
    }

    @Override
    public void deleteJournal(long id) {
        journalService.deleteJournal(id);
    }

    @Override
    public JournalRespDto updateJournal(long journalId, JournalReqDto journalReqDto) {
        Journal journal = journalService.updateJournal(journalId, transformer.transform(journalReqDto, Journal.class));
        return transformer.transform(journal, JournalRespDto.class);
    }
}
