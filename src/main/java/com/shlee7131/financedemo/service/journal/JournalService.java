package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.Journal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JournalService {
    Journal createJournal(Journal journal);
    Page<Journal> readJournalsWithPage(Account account, Pageable pageable);
    Journal updateJournal(long id, Journal journal);
    void deleteJournal(long id);
}
