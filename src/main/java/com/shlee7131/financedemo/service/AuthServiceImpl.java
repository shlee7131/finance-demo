package com.shlee7131.financedemo.service;

import com.shlee7131.financedemo.SessionStorage;
import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.repository.UserRepository;
import com.shlee7131.financedemo.service.dto.AuthInfoDto;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final SessionStorage sessionStorage;

    @Override
    public Optional<String> login(UserReqDto userReqDto) {
        Optional<User> byEmail = repository.findByEmail(userReqDto.getEmail());

        if (byEmail.isEmpty()) return Optional.empty();

        User user = byEmail.get();

        if (!user.matchPassword(userReqDto.getPassword())) return Optional.empty();


        AuthInfoDto authInfoDto = transform(user, AuthInfoDto.class);
        String sessionId = createSessionId(userReqDto);
        sessionStorage.put(sessionId, authInfoDto);

        return Optional.ofNullable(sessionId);

    }

    public String createSessionId(UserReqDto userReqDto) {
        // TODO: session ID 암호화
        return userReqDto.getEmail();
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
