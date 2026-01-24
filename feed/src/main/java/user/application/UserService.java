package user.application;

import lombok.RequiredArgsConstructor;
import user.application.dto.CreateUserRequest;
import user.application.interfaces.UserRepository;
import user.domain.Info;
import user.domain.User;

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
