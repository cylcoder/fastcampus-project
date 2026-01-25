package com.example.feed.user.application;

import static com.example.feed.fake.FakeObjectFactory.getRELATION_SERVICE;
import static com.example.feed.fake.FakeObjectFactory.getUserService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.application.dto.FollowRequest;
import com.example.feed.user.domain.User;

class RelationServiceTest {

  private final UserService userService = getUserService();
  private final RelationService relationService = getRELATION_SERVICE();

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
    relationService.follow(followRequest);

    // then
    assertThat(user1.getFollowingCount()).isEqualTo(1);
    assertThat(user2.getFollowerCount()).isEqualTo(1);
  }

  @Test
  void givenOneUserIsFollowingAnother_whenOneTryToFollowAgain_thenThrowException() {
    // given
    relationService.follow(followRequest);

    // when & then
    assertThatThrownBy(() -> relationService.follow(followRequest))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenUser_whenFollowOneself_thenThrowException() {
    FollowRequest request = new FollowRequest(user1.getId(), user1.getId());
    assertThatThrownBy(() -> relationService.follow(request))
        .isInstanceOf(IllegalArgumentException.class);
  }

}