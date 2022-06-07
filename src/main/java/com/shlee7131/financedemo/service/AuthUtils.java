package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.SessionStorage;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthUtils {
    private final SessionStorage storage;
    private final UserService userService;

    public User getLoggedInUser(String sessionId) {
        AuthInfoDto authInfoDto = storage.get(sessionId);
        Optional<User> user = userService.readUserById(authInfoDto.getId());
        if (user.isEmpty()) throw new ResourceNotFoundException("회원 정보를 찾을 수 없습니다");
        return user.get();
    }
}
