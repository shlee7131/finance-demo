package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.CurrencyCode;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AccountReqDto {
    @NotBlank
    private CurrencyCode currencyCode;
}
