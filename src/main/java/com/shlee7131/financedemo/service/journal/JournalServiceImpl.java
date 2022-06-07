package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;
    
    @Override
    public Journal createJournal(Journal journal) {
        return null;
    }

    @Override
    public Page<Journal> readJournalsWithPage(User user, Pageable pageable) {
        return null;
    }

    @Override
    public Journal updateJournal(long id, Journal journal) {
        return null;
    }

    @Override
    public void deleteJournal(long id) {

    }
}
