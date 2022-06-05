package com.shlee7131.financedemo.service.user;

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
public class UserAdapterImpl implements UserAdapter{
    private final UserService userService;

    @Override
    public UserRespDto createUser(UserReqDto userReqDto) {
        User user = userService.createUser(transform(userReqDto, User.class));
        return transform(user, UserRespDto.class);
    }

    @Override
    public Optional<UserRespDto> readUserById(Long id) {
        Optional<User> user = userService.readUserById(id);
        return Optional.ofNullable(transform(user.get(), UserRespDto.class));
    }

    @Override
    public UserRespDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userService.updateUser(userUpdateDto.getId(), transform(userUpdateDto, User.class));
        return transform(user, UserRespDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
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
