package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.repository.UserRepository;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Override
    public Optional<UserRespDto> createUser(UserReqDto userReqDto) {
        // 중복 등록 방지
        Optional<User> byEmail = repository.findByEmail(userReqDto.getEmail());
        if (byEmail.isPresent()) throw new BadRequestException("회원정보 중복입니다");

        User user = transform(userReqDto, User.class);
        UserRespDto userRespDto = transform(user, UserRespDto.class);

        repository.save(user);

        return Optional.ofNullable(userRespDto);
    }

    @Override
    public Optional<UserRespDto> readUserById(Long id) {
        Optional<User> find = repository.findById(id);

        if (find.isEmpty()) return Optional.empty();

        UserRespDto userRespDto = transform(find.get(), UserRespDto.class);
        return Optional.ofNullable(userRespDto);
    }

    @Override
    public Optional<UserRespDto> deleteUser(Long id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) return Optional.empty();

        repository.delete(user.get());
        UserRespDto userRespDto = transform(user.get(), UserRespDto.class);
        return Optional.ofNullable(userRespDto);
    }

    @Override
    public Optional<UserRespDto> updateUser(Long id, UserUpdateDto userUpdateDto) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) return Optional.empty();

        User find = user.get();
        find.setPassword(userUpdateDto.getPassword() != null ? userUpdateDto.getPassword() : find.getPassword());

        repository.save(find);
        UserRespDto userRespDto = transform(find, UserRespDto.class);
        return Optional.ofNullable(userRespDto);
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
