package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
