package com.shlee7131.financedemo.controller;


import com.shlee7131.financedemo.exception.ResourceNotFoundException;
import com.shlee7131.financedemo.service.user.UserAdapter;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserAdapter userAdapter;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserRespDto> getUserById(@PathVariable Long id) {
        Optional<UserRespDto> userRespDto = userAdapter.readUserById(id);

        if (userRespDto.isEmpty()) throw new ResourceNotFoundException("유저 정보를 다시 입력해주세요");

        return new ResponseEntity<>(userRespDto.get(), HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id){
        userAdapter.deleteUser(id);
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
    }
}
