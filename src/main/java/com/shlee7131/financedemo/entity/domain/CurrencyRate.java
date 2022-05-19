package com.shlee7131.financedemo.entity.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CurrencyRate {
    private final Integer id;
    private final String currency_code;
    private final double buy_rate;
    private final double sell_rate;
    private final LocalDateTime created_at;
}
