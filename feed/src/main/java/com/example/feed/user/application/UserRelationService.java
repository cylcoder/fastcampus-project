package com.example.feed.user.application;

import lombok.RequiredArgsConstructor;
import com.example.feed.user.application.dto.FollowRequest;
import com.example.feed.user.application.interfaces.UserRelationRepository;
import com.example.feed.user.domain.User;

@RequiredArgsConstructor
public class UserRelationService {

  private final UserService userService;
  private final UserRelationRepository userRelationRepository;

  public void follow(FollowRequest request) {
    User follower = userService.getUser(request.followerId());
    User followee = userService.getUser(request.followeeId());
    if (userRelationRepository.isFollowing(follower, followee)) {
      throw new IllegalArgumentException();
    }
    follower.follow(followee);
    userRelationRepository.save(follower, followee);
  }

  public void unfollow(FollowRequest request) {
    User follower = userService.getUser(request.followerId());
    User followee = userService.getUser(request.followeeId());
    if (!userRelationRepository.isFollowing(follower, followee)) {
      throw new IllegalArgumentException();
    }
    follower.unfollow(followee);
    userRelationRepository.delete(follower, followee);
  }

}
