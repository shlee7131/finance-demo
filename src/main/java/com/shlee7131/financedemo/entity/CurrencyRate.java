package com.shlee7131.financedemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
public class CurrencyRate extends Commons{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private CurrencyCode currency_code;

    private double buy_rate;
    private double sell_rate;

    @CreationTimestamp
    private LocalDate date_currency;

    public void setCurrency_code(CurrencyCode currency_code) {
        this.currency_code = currency_code;
    }

    public void setBuy_rate(double buy_rate) {
        this.buy_rate = buy_rate;
    }

    public void setSell_rate(double sell_rate) {
        this.sell_rate = sell_rate;
    }
}
