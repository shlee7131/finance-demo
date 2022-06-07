package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JournalService {
    Journal createJournal(Journal journal);
    Page<Journal> readJournalsWithPage(User user, Pageable pageable);
    Journal updateJournal(long id, Journal journal);
    void deleteJournal(long id);
}
