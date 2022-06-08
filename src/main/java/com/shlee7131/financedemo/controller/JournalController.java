package com.shlee7131.financedemo.controller;

import com.shlee7131.financedemo.entity.Journal;
import com.shlee7131.financedemo.service.dto.JournalReqDto;
import com.shlee7131.financedemo.service.dto.JournalRespDto;
import com.shlee7131.financedemo.service.journal.JournalAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JournalController {
    private final JournalAdapter journalAdapter;

    @GetMapping("/journals")
    public ResponseEntity<Page<JournalRespDto>> getJournalsWithPage(@PathVariable long id, Pageable pageable) {
        Page<JournalRespDto> journalRespDtos = journalAdapter.readJournalsWithPage(id, pageable);
        return new ResponseEntity<>(journalRespDtos, HttpStatus.OK);
    }

    @PostMapping("/journals/deposit")
    public ResponseEntity<JournalRespDto> deposit(@RequestBody JournalReqDto journalReqDto) {
        JournalRespDto journalRespDto = journalAdapter.deposit(journalReqDto);
        return new ResponseEntity<>(journalRespDto, HttpStatus.CREATED);
    }

    @PostMapping("/journals/withdraw")
    public ResponseEntity<JournalRespDto> withdraw(@RequestBody JournalReqDto journalReqDto) {
        JournalRespDto journalRespDto = journalAdapter.withdraw(journalReqDto);
        return new ResponseEntity<>(journalRespDto, HttpStatus.CREATED);
    }

    @PutMapping("/journals/{id}")
    public ResponseEntity<JournalRespDto> updateJournal(@PathVariable long id, @RequestBody JournalReqDto journalReqDto) {
        JournalRespDto journalRespDto = journalAdapter.updateJournal(id, journalReqDto);
        return new ResponseEntity<>(journalRespDto, HttpStatus.OK);
    }

    @DeleteMapping("/accounts/{id}/journals/{journal}")
    public ResponseEntity<?> deleteJournal(@PathVariable("journal") long id) {
        journalAdapter.deleteJournal(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
