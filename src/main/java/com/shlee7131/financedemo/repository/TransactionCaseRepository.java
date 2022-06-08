package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.TransactionCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionCaseRepository extends JpaRepository<TransactionCase, Long> {
}
