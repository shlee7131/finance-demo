package com.shlee7131.financedemo.service.dto;

import com.shlee7131.financedemo.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthInfoDto {
    private Long id;
    private String email;
    private List<Account> accounts;
}