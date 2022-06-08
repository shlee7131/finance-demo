package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.AccountSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountSubjectRepository extends JpaRepository<AccountSubject, Long> {
}
