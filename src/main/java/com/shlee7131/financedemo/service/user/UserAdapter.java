package com.shlee7131.financedemo.service.user;

import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;

import java.util.Optional;

public interface UserAdapter {
    UserRespDto createUser(UserReqDto userReqDto);
    Optional<UserRespDto> readUserById(Long id);
    UserRespDto updateUser(UserUpdateDto userUpdateDto);
    void deleteUser(Long id);
}
