package com.shlee7131.financedemo.entity;

import com.shlee7131.financedemo.entity.enums.CurrencyCode;
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
    private CurrencyCode currencyCode;

    private double buyRate;
    private double sellRate;

    @CreationTimestamp
    private LocalDate queriedDate;

    public void setCurrencyCode(CurrencyCode currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setBuyRate(double buyRate) {
        this.buyRate = buyRate;
    }

    public void setSellRate(double sellRate) {
        this.sellRate = sellRate;
    }
}
