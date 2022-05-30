package com.shlee7131.financedemo.service;


import com.shlee7131.financedemo.service.dto.*;

import javax.transaction.Transactional;
import java.util.Optional;



public interface UserService {
    Optional<UserRespDto> createUser(UserReqDto userReqDto);
    Optional<UserRespDto> readUserById(Long id);
    Optional<UserRespDto> updateUser(Long id, UserUpdateDto userUpdateDto);
    Optional<UserRespDto> deleteUser(Long id);
    boolean login(UserReqDto userReqDto);
}
