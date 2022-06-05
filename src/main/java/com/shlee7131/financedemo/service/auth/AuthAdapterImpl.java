package com.shlee7131.financedemo.service.auth;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.Transformer;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthAdapterImpl implements AuthAdapter {
    private final UserService userService;
    private final AuthService authService;
    private final Transformer transformer;

    @Override
    public AuthInfoDto register(UserReqDto userReqDto) {
        User user = transformer.transform(userReqDto, User.class);
        User registered = userService.createUser(user);
        return transformer.transform(registered, AuthInfoDto.class);
    }

    @Override
    public Optional<String> login(UserReqDto userReqDto) {
        return authService.login(userReqDto);
    }
}
