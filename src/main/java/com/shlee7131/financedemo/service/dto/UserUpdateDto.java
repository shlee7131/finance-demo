package com.shlee7131.financedemo.service.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserUpdateDto {
    private Long id;
    @Size(min=3, message = "비밀번호는 3자리 이상이어야 합니다.")
    private String password;
}
