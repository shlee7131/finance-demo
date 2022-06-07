package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.enums.CurrencyCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountRespDto {
    private Long id;
    private CurrencyCode currencyCode;
}
