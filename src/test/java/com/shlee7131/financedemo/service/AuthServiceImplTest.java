package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.SessionStorage;
import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class AuthServiceImplTest {
    TestConfigContainer container = TestConfigContainer.getInstance();
    AuthService authService = container.getBean(AuthService.class);
    UserService userService = container.getBean(UserService.class);
    SessionStorage sessionStorage = container.getBean(SessionStorage.class);

    @Test
    void 로그인_성공(){
        UserReqDto userReqDto = new UserReqDto("shlee7131@gmail.com", "asdf");
        Optional<UserRespDto> user = userService.createUser(userReqDto);
        Optional<String> sessionId = authService.login(userReqDto);
        AuthInfoDto authInfoDto = sessionStorage.get(sessionId.get());

        assertThat(authInfoDto.getEmail()).isEqualTo(user.get().getEmail());
    }

    @Test
    void 인증_실패(){
        UserReqDto userReqDto = new UserReqDto("shlee7132@gmail.com", "asdf");
        UserReqDto userReqDtoFailure = new UserReqDto("shlee7132@gmail.com", "asdffa");

        Optional<UserRespDto> user = userService.createUser(userReqDto);
        Optional<String> sessionId = authService.login(userReqDtoFailure);

        assertThat(sessionId).isEmpty();
    }
}