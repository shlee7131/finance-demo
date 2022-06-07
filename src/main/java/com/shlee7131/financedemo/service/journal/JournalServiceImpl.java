package com.shlee7131.financedemo.service.journal;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final JournalRepository journalRepository;

    @Override
    public Journal createJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    @Override
    public Page<Journal> readJournalsWithPage(Account account, Pageable pageable) {
        return journalRepository.findAllByAccount(account, pageable);
    }

    @Override
    public Journal updateJournal(long id, Journal journal) {
        Optional<Journal> byId = journalRepository.findById(id);
        if (byId.isEmpty()) throw new ResourceNotFoundException("해당 원장을 찾을 수 없습니다");

        Journal original = byId.get();

        original.setBriefs(journal.getBriefs() != null ? journal.getBriefs() : original.getBriefs());
        original.setCreditAmount(journal.getCreditAmount() != 0 ? journal.getCreditAmount() : original.getCreditAmount());
        original.setDebitAmount(journal.getDebitAmount() != 0 ? journal.getDebitAmount() : original.getDebitAmount());
        original.setCreditSign(journal.getCreditSign() != null ? journal.getCreditSign() : original.getCreditSign());
        original.setDebitSign(journal.getDebitSign() != null ? journal.getDebitSign() : original.getDebitSign());
        original.setDebitSubject(journal.getDebitSubject() != null ? journal.getDebitSubject() : original.getDebitSubject());
        original.setCreditSubject(journal.getCreditSubject() != null ? journal.getCreditSubject() : original.getCreditSubject());

        return original;
    }

    @Override
    public void deleteJournal(long id) {
        Optional<Journal> byId = journalRepository.findById(id);
        if (byId.isEmpty()) throw new ResourceNotFoundException("해당 원장을 찾을 수 없습니다");
        journalRepository.deleteById(id);
    }
}
