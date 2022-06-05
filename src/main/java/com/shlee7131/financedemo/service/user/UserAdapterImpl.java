package com.shlee7131.financedemo.service.user;

import com.shlee7131.financedemo.entity.User;
import com.shlee7131.financedemo.service.Transformer;
import com.shlee7131.financedemo.service.dto.UserReqDto;
import com.shlee7131.financedemo.service.dto.UserRespDto;
import com.shlee7131.financedemo.service.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapterImpl implements UserAdapter{
    private final UserService userService;
    private final Transformer transformer;

    @Override
    public UserRespDto createUser(UserReqDto userReqDto) {
        User user = userService.createUser(transformer.transform(userReqDto, User.class));
        return transformer.transform(user, UserRespDto.class);
    }

    @Override
    public Optional<UserRespDto> readUserById(Long id) {
        Optional<User> user = userService.readUserById(id);
        return Optional.ofNullable(transformer.transform(user.get(), UserRespDto.class));
    }

    @Override
    public UserRespDto updateUser(UserUpdateDto userUpdateDto) {
        User user = userService.updateUser(userUpdateDto.getId(), transformer.transform(userUpdateDto, User.class));
        return transformer.transform(user, UserRespDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
