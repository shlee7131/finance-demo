package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;

import java.util.Optional;

public interface UserAdapter {
    Optional<UserRespDto> createUser(UserReqDto userReqDto);
    Optional<UserRespDto> readUser(UserReqDto userReqDto);
    Optional<UserRespDto> updateUser(UserUpdateDto userUpdateDto);
    Optional<UserRespDto> deleteUser(UserReqDto userReqDto);
}
