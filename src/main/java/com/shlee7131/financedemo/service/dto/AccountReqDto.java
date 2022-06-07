package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.CurrencyCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class AccountReqDto {
    @NotBlank
    private CurrencyCode currencyCode;
}
