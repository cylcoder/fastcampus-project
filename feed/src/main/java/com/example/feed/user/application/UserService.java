package com.example.feed.user.application;

import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.application.dto.UserResponse;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.Info;
import com.example.feed.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User createUser(CreateUserRequest request) {
    Info info = new Info(request.name(), request.profileImageUrl());
    User user = new User(null, info);
    return userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository.findById(id);
  }

  public UserResponse getUserProfile(Long id) {
    User user = getUser(id);
    return UserResponse.from(user);
  }

}
