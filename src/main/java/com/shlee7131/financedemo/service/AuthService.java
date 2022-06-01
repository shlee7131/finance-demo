package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthService {
    Optional<String> login(UserReqDto userReqDto);
}
