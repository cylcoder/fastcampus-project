package org.fastcampus.user.application;

import org.fastcampus.user.application.dto.CreateUserRequestDto;
import org.fastcampus.user.application.dto.FollowUserRequestDto;
import org.fastcampus.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.fastcampus.fake.FakeObjectFactory.getUserRelationalService;
import static org.fastcampus.fake.FakeObjectFactory.getUserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {

    private final UserService userService = getUserService();
    private final UserRelationService userRelationService = getUserRelationalService();

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init() {
        CreateUserRequestDto dto = new CreateUserRequestDto("John", "https://s3.amazonaws.com/john");
        user1 = userService.createUser(dto);
        user2 = userService.createUser(dto);
        requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    void givenCreateTwoUsers_whenFollow_thenUserFollowSaved() {
        // when
        userRelationService.follow(requestDto);

        // then
        assertEquals(1, user1.getFollowingCount());
        assertEquals(1, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUsersFollowed_whenFollow_thenUserThrowError() {
        // given
        userRelationService.follow(requestDto);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenFollowHimself_thenUserThrowError() {
        // given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(dto));
    }

    @Test
    void givenCreateTwoUsersFollow_whenUnfollow_thenUserFollowSaved() {
        // given
        userRelationService.follow(requestDto);

        // when
        userRelationService.unfollow(requestDto);

        // then
        assertEquals(0, user1.getFollowingCount());
        assertEquals(0, user2.getFollowerCount());
    }

    @Test
    void givenCreateTwoUsers_whenUnfollow_thenUserThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(requestDto));
    }

    @Test
    void givenCreateOneUser_whenUnfollowHimself_thenUserThrowError() {
        // given
        FollowUserRequestDto dto = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(dto));
    }

}