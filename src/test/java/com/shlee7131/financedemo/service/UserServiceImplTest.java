package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.TestConfigContainer;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.repository.UserRepository;
import com.shlee7131.financedemo.service.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
class UserServiceImplTest {
    TestConfigContainer container = TestConfigContainer.getInstance();
    UserService userService = container.getBean(UserService.class);
    UserRepository userRepository = container.getBean(UserRepository.class);

    @Test
    void 회원가입_성공(){
        UserReqDto userReqDto = new UserReqDto("shlee7131@gmail.com","1234");
        Optional<UserRespDto> user = userService.createUser(userReqDto);
        assertThat(userReqDto.getEmail()).isEqualTo(user.get().getEmail());
    }

    @Test
    void 회원가입_이메일중복_실패(){
        UserReqDto userReqDto1 = new UserReqDto("shlee7132@gmail.com","1234");
        UserReqDto userReqDto2 = new UserReqDto("shlee7132@gmail.com","112345");

        userService.createUser(userReqDto1);
        Optional<UserRespDto> user = userService.createUser(userReqDto2);
        assertThat(user).isEmpty();
    }

    @Test
    void 회원탈퇴(){
        UserReqDto userReqDto = new UserReqDto("shlee7133@gmail.com","1234");
        Optional<UserRespDto> user = userService.createUser(userReqDto);
        User find = userRepository.findByEmail("shlee7133@gmail.com").get();

        Optional<UserRespDto> userRespDto = userService.deleteUser(find.getId());

        assertThat(userRespDto.get()).isEqualTo(user.get());
    }

    @Test
    void 회원수정_비밀번호(){
        UserReqDto userReqDto = new UserReqDto("shlee7134@gmail.com","1234");
        UserRespDto userRespDto = userService.createUser(userReqDto).get();
        UserUpdateDto userUpdateDto = new UserUpdateDto("12");
        User user = userRepository.findByEmail("shlee7134@gmail.com").get();

        userService.updateUser(user.getId(), userUpdateDto);
        User find = userRepository.findById(user.getId()).get();

        assertThat(find.getPassword()).isEqualTo("12");
    }

    @Test
    void 회원정보(){
        UserReqDto userReqDto = new UserReqDto("shlee7135@gmail.com","1234");
        UserRespDto userRespDto = userService.createUser(userReqDto).get();
        Optional<User> user = userRepository.findByEmail("shlee7135@gmail.com");
        assertThat(userRespDto.getEmail()).isEqualTo("shlee7135@gmail.com");
        assertThat(userRespDto.getAccount_id()).isEqualTo(user.get().getAccount_id());
    }

}