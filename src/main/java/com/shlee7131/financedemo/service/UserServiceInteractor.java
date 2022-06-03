package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceInteractor implements UserAdapter{
    private final UserService userService;

    @Override
    public Optional<UserRespDto> createUser(UserReqDto userReqDto) {
        Optional<User> user = userService.createUser(transform(userReqDto, User.class));
        return Optional.ofNullable(transform(user.get(), UserRespDto.class));
    }

    @Override
    public Optional<UserRespDto> readUser(UserReqDto userReqDto) {
        return Optional.empty();
    }

    @Override
    public Optional<UserRespDto> updateUser(UserUpdateDto userUpdateDto) {
        return Optional.empty();
    }

    @Override
    public Optional<UserRespDto> deleteUser(UserReqDto userReqDto) {
        return Optional.empty();
    }

    public <T,R> R transform(T t, Class<R> type) {
        R object = null;
        try {
            object = type.getConstructor().newInstance();
            BeanUtils.copyProperties(t, object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return object;
    }
}
