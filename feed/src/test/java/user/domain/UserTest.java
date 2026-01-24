package user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class UserTest {

  private final Info info1 = new Info("name1", "url1");
  private final Info info2 = new Info("name2", "url2");

  @Test
  void givenTwoUsers_whenEqual_thenReturnFalse() {
    // given
    User user1 = new User(1L, info1);
    User user2 = new User(2L, info2);

    // when & then
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void givenTwoSameIdUsers_whenEqual_thenReturnTrue() {
    // given
    User user1 = new User(1L, info1);
    User user2 = new User(1L, info1);

    // when & then
    assertThat(user1).isEqualTo(user2);
  }

  @Test
  void givenTwoUsersWithSameIdButDifferentInfo_whenEqual_thenReturnFalse() {
    // given
    User user1 = new User(1L, info1);
    User user2 = new User(1L, info2);

    // when & then
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void givenTwoUsers_WhenUser1FollowsUser2_thenCountChanges() {
    // given
    User user1 = new User(1L, info1);
    User user2 = new User(2L, info2);

    // when
    user1.follow(user2);

    // then
    assertThat(user1)
        .returns(1, User::getFollowingCount)
        .returns(0, User::getFollowerCount);

    assertThat(user2)
        .returns(0, User::getFollowingCount)
        .returns(1, User::getFollowerCount);
  }

  @Test
  void givenUser1IsFollowingUser2_WhenUser1UnfollowsUser2_thenCountChanges() {
    // given
    User user1 = new User(1L, info1);
    User user2 = new User(2L, info2);
    user1.follow(user2);

    // when
    user1.unfollow(user2);

    // then
    assertThat(user1)
        .returns(0, User::getFollowingCount)
        .returns(0, User::getFollowerCount);

    assertThat(user2)
        .returns(0, User::getFollowingCount)
        .returns(0, User::getFollowerCount);
  }

}