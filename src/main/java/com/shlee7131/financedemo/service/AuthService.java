package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.service.dto.UserReqDto;


import java.util.Optional;

public interface AuthService {
    Optional<String> login(UserReqDto userReqDto);
}
