package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.infra.repo.UserRepository;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;
import com.sun.xml.bind.v2.runtime.output.SAXOutput;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private Integer account_id_sequence = 0;

    @Override
    public Optional<UserRespDto> createUser(UserReqDto userReqDto) {
        // 중복 등록 방지
        Optional<User> byEmail = repository.findByEmail(userReqDto.getEmail());
        if (byEmail.isPresent()) return Optional.empty();

        User user = transform(userReqDto, User.class);
        user.setAccount_id(++account_id_sequence);
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

    @Override
    public boolean login(UserReqDto userReqDto) {
        Optional<User> byEmail = repository.findByEmail(userReqDto.getEmail());
        if (byEmail.isEmpty()) return false;
        User user = byEmail.get();
        if (user.matchPassword(userReqDto.getPassword())) return true;
        return false;
    }

    public <T,R> R transform(T t, Class<R> type) {
        Object object = null;
        try {
            object = type.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        BeanUtils.copyProperties(t, object);

        return (R)object;
    }
}
