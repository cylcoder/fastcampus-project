package user.domain;

import common.domain.PositiveIntegerCount;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class User {

  private final Long id;
  private final Info info;
  private final PositiveIntegerCount followingPositiveIntegerCount;
  private final PositiveIntegerCount followerPositiveIntegerCount;

  public User(Long id, Info info) {
    this.id = id;
    this.info = info;
    this.followingPositiveIntegerCount = new PositiveIntegerCount();
    this.followerPositiveIntegerCount = new PositiveIntegerCount();
  }

  public void follow(User user) {
    if (this.equals(user)) {
      throw new IllegalArgumentException();
    }

    followingPositiveIntegerCount.increase();
    user.increaseFollowerCount();
  }

  public void unfollow(User user) {
    if (this.equals(user)) {
      throw new IllegalArgumentException();
    }
    followingPositiveIntegerCount.decrease();
    user.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerPositiveIntegerCount.increase();
  }

  private void decreaseFollowerCount() {
    followerPositiveIntegerCount.decrease();
  }

}
