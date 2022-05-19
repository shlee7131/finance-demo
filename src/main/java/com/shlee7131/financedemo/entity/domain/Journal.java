package com.shlee7131.financedemo.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Journal {
    private final Integer id;
    private final int account_id;
    private final int debit_code;
    private final int debit_sign;
    private final int debit_amount;
    private final int credit_code;
    private final int credit_sign;
    private final int credit_amount;
    private final String briefs;
    private final LocalDateTime created_at;
}
