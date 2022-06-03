package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.CurrencyCode;
import lombok.Data;

@Data
public class AccountRespDto {
    private Long id;
    private CurrencyCode currencyCode;
}
