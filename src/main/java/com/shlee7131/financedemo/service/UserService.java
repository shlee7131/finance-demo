package com.shlee7131.financedemo.service;


import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.dto.*;

import java.util.Optional;



public interface UserService {
    Optional<User> createUser(User userReqDto);
    Optional<User> readUserById(Long id);
    Optional<User> updateUser(Long id, User userUpdateDto);
    Optional<User> deleteUser(Long id);
}
