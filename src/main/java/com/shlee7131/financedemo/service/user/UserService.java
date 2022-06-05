package com.shlee7131.financedemo.service.user;


import com.shlee7131.financedemo.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UserService {
    User createUser(User userReqDto);
    Optional<User> readUserById(Long id);
    Optional<User> updateUser(Long id, User user);
    void deleteUser(Long id);
}
