package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.repository.UserRepository;
import com.shlee7131.financedemo.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // SpringBootTest 환경에서 사용시 모든 트랜잭션은 자동으로 rollback 되면 종료
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    // Spring Data JPA 가 기본으로제공하는 메소드들은 모두 기본적으로 @Transactional 되어 있음
    @Test
    void 회원가입_성공(){
        User user = new User("shlee7132@gmail.com","1234");
        User created = userService.createUser(user);
        assertThat(user).isEqualTo(created);
    }

    @Test
    void 회원가입_이메일중복_실패(){
        User user1 = new User("shlee7132@gmail.com","1234");
        User user2 = new User("shlee7132@gmail.com","112345");

        Assertions.assertThrowsExactly(BadRequestException.class,
                () -> {
                    userService.createUser(user1);
                    userService.createUser(user2);
                }, "회원정보 중복입니다");
    }

    @Test
    void 회원탈퇴(){
        User user = new User("shlee7132@gmail.com","1234");
        User created = userService.createUser(user);
        Long id = created.getId();


        userService.deleteUser(id);
        Optional<User> byId = userRepository.findById(id);


        assertThat(byId).isEmpty();
    }

    @Test
    void 회원수정_비밀번호(){
        User user = new User("shlee7132@gmail.com","1234");

        User created = userService.createUser(user);
        Long id = user.getId();
        User toUpdate = new User();
        toUpdate.setPassword("asdf");

        User updated = userService.updateUser(id, toUpdate).get();

        assertThat(updated.getPassword()).isEqualTo("asdf");
    }

    @Test
    void 회원정보_조회(){
        User user = new User("shlee7132@gmail.com","1234");

        User created = userService.createUser(user);

        User find = userRepository.findById(created.getId()).get();

        assertThat(find.getEmail()).isEqualTo("shlee7132@gmail.com");
        assertThat(find.getPassword()).isEqualTo("1234");
        assertThat(find.getId()).isEqualTo(created.getId());
    }
}