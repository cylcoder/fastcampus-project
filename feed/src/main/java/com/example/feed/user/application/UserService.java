package com.example.feed.user.application;

import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.Info;
import com.example.feed.user.domain.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User createUser(CreateUserRequest request) {
    Info info = new Info(request.name(), request.profileImageUrl());
    User user = new User(null, info);
    return userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow();
  }

}
