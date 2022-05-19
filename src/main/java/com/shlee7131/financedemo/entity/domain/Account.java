package com.shlee7131.financedemo.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@AllArgsConstructor
public class Account {
    private final Integer id;
    private final int user_id;
    private final int currency_code;
    private final String name;
}
