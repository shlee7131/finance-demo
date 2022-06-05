package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.SessionStorage;
import com.shlee7131.financedemo.service.auth.AuthAdapter;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class AuthServiceImplTest {
    @Autowired
    AuthAdapter authAdapter;
    @Autowired
    SessionStorage sessionStorage;

    @Test
    void 로그인_성공(){
        UserReqDto userReqDto = new UserReqDto("shlee7131@gmail.com", "asdf");
        AuthInfoDto register = authAdapter.register(userReqDto);
        Optional<String> sessionId = authAdapter.login(userReqDto);
        AuthInfoDto authInfoDto = sessionStorage.get(sessionId.get());

        assertThat(authInfoDto).isEqualTo(register);
    }

    @Test
    void 인증_실패(){
        UserReqDto userReqDto = new UserReqDto("shlee7131@gmail.com", "asdf");
        UserReqDto userReqDtoFailure = new UserReqDto("shlee7131@gmail.com", "asdffa");

        AuthInfoDto register = authAdapter.register(userReqDto);
        Optional<String> sessionId = authAdapter.login(userReqDtoFailure);

        assertThat(sessionId).isEmpty();
    }
}