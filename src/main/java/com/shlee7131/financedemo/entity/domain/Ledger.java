package com.shlee7131.financedemo.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Ledger {
    private final Integer id;
    private final int account_code;
    private final int debit_amount;
    private final int credit_amount;
    private final String briefs;
    private final LocalDateTime created_at;
}
