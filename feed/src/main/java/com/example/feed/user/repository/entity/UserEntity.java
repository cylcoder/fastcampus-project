package com.example.feed.user.repository.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.example.feed.common.domain.PositiveIntegerCount;
import com.example.feed.common.repository.entity.BaseTimeEntity;
import com.example.feed.user.domain.Info;
import com.example.feed.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String name;
  private String profileImageUrl;
  private int followingCount;
  private int followerCount;

  public User toUser() {
    return User.builder()
        .id(id)
        .info(new Info(name, profileImageUrl))
        .followingPositiveIntegerCount(new PositiveIntegerCount(followingCount))
        .followerPositiveIntegerCount(new PositiveIntegerCount(followerCount))
        .build();
  }

  public static UserEntity fromUser(User user) {
    return UserEntity.builder()
        .id(user.getId())
        .name(user.getInfo().getName())
        .profileImageUrl(user.getInfo().getProfileImageUrl())
        .followingCount(user.getFollowingPositiveIntegerCount().getCount())
        .followerCount(user.getFollowerPositiveIntegerCount().getCount())
        .build();
  }

}
