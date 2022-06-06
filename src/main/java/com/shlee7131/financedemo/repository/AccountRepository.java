package com.shlee7131.financedemo.repository;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Page<Account> findAllByUser(User user, Pageable pageable);
}
