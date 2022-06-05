package com.shlee7131.financedemo.service.user;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.exception.BadRequestException;
import com.shlee7131.financedemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;

    @Override
    public User createUser(User user) {
        // 중복 등록 방지
        Optional<User> byEmail = repository.findByEmail(user.getEmail());
        if (byEmail.isPresent()) throw new BadRequestException("회원정보 중복입니다");
        return repository.save(user);
    }

    @Override
    public Optional<User> readUserById(Long id) {
        Optional<User> byId = repository.findById(id);
        if (byId.isEmpty()) return Optional.empty();
        User user = byId.get();
        return Optional.ofNullable(user);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> byId = readUserById(id);
        if (byId.isEmpty()) throw new BadRequestException("존재하지 않는 유저입니다");
        User user = byId.get();
        repository.delete(user);
    }

    @Override
    public Optional<User> updateUser(Long id, User user) {
        Optional<User> byId = repository.findById(id);

        if (byId.isEmpty()) return Optional.empty();

        User find = byId.get();
        find.setPassword(user.getPassword() != null ? user.getPassword() : find.getPassword());

        repository.save(find);
        return Optional.ofNullable(find);
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
