package com.example.feed.user.application.interfaces;

import com.example.feed.user.domain.User;

public interface RelationRepository {

  boolean isFollowing(User follower, User followee);

  void save(User follower, User followee);

  void delete(User follower, User followee);

}