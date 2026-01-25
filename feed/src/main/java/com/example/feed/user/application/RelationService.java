package com.example.feed.user.application;

import lombok.RequiredArgsConstructor;
import com.example.feed.user.application.dto.FollowRequest;
import com.example.feed.user.application.interfaces.RelationRepository;
import com.example.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RelationService {

  private final UserService userService;
  private final RelationRepository relationRepository;

  public void follow(FollowRequest request) {
    User follower = userService.getUser(request.followerId());
    User followee = userService.getUser(request.followeeId());
    if (relationRepository.isFollowing(follower, followee)) {
      throw new IllegalArgumentException();
    }
    follower.follow(followee);
    relationRepository.save(follower, followee);
  }

  public void unfollow(FollowRequest request) {
    User follower = userService.getUser(request.followerId());
    User followee = userService.getUser(request.followeeId());
    if (!relationRepository.isFollowing(follower, followee)) {
      throw new IllegalArgumentException();
    }
    follower.unfollow(followee);
    relationRepository.delete(follower, followee);
  }

}
