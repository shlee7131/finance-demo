package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.Account;
import com.shlee7131.financedemo.entity.AccountSubject;
import com.shlee7131.financedemo.entity.enums.JournalAccountSign;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class JournalReqDto {
    private Account account;
    private long accountId;
    private String transactionType;
    private int amount;
    private String briefs;
}
