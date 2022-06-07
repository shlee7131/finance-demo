package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.Journal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<Journal, Long> {
}
