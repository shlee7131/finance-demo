package com.shlee7131.financedemo.entity.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class User {
    private final Integer id;
    private final int account_number;
    private final LocalDateTime created_at;
}
