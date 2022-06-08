package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LedgerRepository extends JpaRepository<Ledger, Long> {
}
