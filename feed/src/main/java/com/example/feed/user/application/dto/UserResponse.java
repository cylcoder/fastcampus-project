package com.example.feed.user.application.dto;

import com.example.feed.user.domain.User;
import lombok.Builder;

@Builder
public record UserResponse(
    long id,
    String name,
    String profileImageUrl,
    int followingCount,
    int followerCount
) {

  public static UserResponse from(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .name(user.getInfo().getName())
        .profileImageUrl(user.getInfo().getProfileImageUrl())
        .followingCount(user.getFollowingCount())
        .followerCount(user.getFollowerCount())
        .build();
  }
}
