package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.service.dto.JournalReqDto;
import com.shlee7131.financedemo.service.dto.JournalRespDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JournalAdapter {
    JournalRespDto deposit(JournalReqDto journalReqDto);
    JournalRespDto withdraw(JournalReqDto journalReqDto);
    Page<JournalRespDto> readJournalsWithPage(long accountId, Pageable pageable);
    void deleteJournal(long id);
    JournalRespDto updateJournal(long journalId, JournalReqDto journalReqDto);
}
