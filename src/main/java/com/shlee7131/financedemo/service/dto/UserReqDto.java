package com.shlee7131.financedemo.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserReqDto {
    @NotBlank
    @Email(message = "이메일 형식을 지켜주세요")
    private String email;

//    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min=3, message = "비밀번호는 3자리 이상이어야 합니다.")
    private String password;
}
