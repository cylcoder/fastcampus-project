package com.example.feed.user.application;

import static com.example.feed.fake.FakeObjectFactory.getUserRelationService;
import static com.example.feed.fake.FakeObjectFactory.getUserService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.application.dto.FollowRequest;
import com.example.feed.user.domain.User;

class UserRelationServiceTest {

  private final UserService userService = getUserService();
  private final UserRelationService userRelationService = getUserRelationService();

  private User user1;
  private User user2;
  private FollowRequest followRequest;

  @BeforeEach
  void setUp() {
    CreateUserRequest createUserRequest = new CreateUserRequest("foo", "bar");
    user1 = userService.createUser(createUserRequest);
    user2 = userService.createUser(createUserRequest);
    followRequest = new FollowRequest(user1.getId(), user2.getId());
  }

  @Test
  void givenTwoUsers_whenOneUserFollowsAnother_thenCountIsChanged() {
    // when
    userRelationService.follow(followRequest);

    // then
    assertThat(user1.getFollowingCount()).isEqualTo(1);
    assertThat(user2.getFollowerCount()).isEqualTo(1);
  }

  @Test
  void givenOneUserIsFollowingAnother_whenOneTryToFollowAgain_thenThrowException() {
    // given
    userRelationService.follow(followRequest);

    // when & then
    assertThatThrownBy(() -> userRelationService.follow(followRequest))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenUser_whenFollowOneself_thenThrowException() {
    FollowRequest request = new FollowRequest(user1.getId(), user1.getId());
    assertThatThrownBy(() -> userRelationService.follow(request))
        .isInstanceOf(IllegalArgumentException.class);
  }

}