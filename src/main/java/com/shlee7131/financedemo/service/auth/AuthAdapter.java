package com.shlee7131.financedemo.service.auth;


import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;

import java.util.Optional;

public interface AuthAdapter {
    AuthInfoDto register(UserReqDto userReqDto);
    Optional<String> login(UserReqDto userReqDto);
}
