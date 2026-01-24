package com.example.feed.user.domain;

import com.example.feed.common.domain.PositiveIntegerCount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id", "info"})
@Builder
public class User {

  private final Long id;
  private final Info info;
  private final PositiveIntegerCount followingPositiveIntegerCount;
  private final PositiveIntegerCount followerPositiveIntegerCount;

  public User(Long id, Info info) {
    if (info == null) {
      throw new IllegalArgumentException();
    }

    this.id = id;
    this.info = info;
    this.followingPositiveIntegerCount = new PositiveIntegerCount();
    this.followerPositiveIntegerCount = new PositiveIntegerCount();
  }

  public int getFollowingCount() {
    return followingPositiveIntegerCount.getCount();
  }

  public int getFollowerCount() {
    return followerPositiveIntegerCount.getCount();
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
