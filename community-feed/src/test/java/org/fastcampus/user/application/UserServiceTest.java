package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.fastcampus.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUserInfoDto_whenCreateUser_thenCanFindUser() {
        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("John", "https://s3.amazonaws.com/john");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User foundUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();
        assertEquals(foundUser, savedUser);
        assertEquals("John", userInfo.getName());
    }

}