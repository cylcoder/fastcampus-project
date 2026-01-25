package com.example.feed.user.ui;

import com.example.feed.common.ui.DataResponse;
import com.example.feed.user.application.UserService;
import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.application.dto.FollowUserResponse;
import com.example.feed.user.application.dto.UserResponse;
import com.example.feed.user.domain.User;
import com.example.feed.user.repository.jpa.JpaUserQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;
  private final JpaUserQueryRepository jpaUserQueryRepository;

  @PostMapping
  public DataResponse<Long> createUser(@RequestBody CreateUserRequest request) {
    User user = userService.createUser(request);
    return DataResponse.ok(user.getId());
  }

  @GetMapping("/{userId}/followers")
  public DataResponse<List<FollowUserResponse>> getFollowers(@PathVariable Long userId) {
    return DataResponse.ok(jpaUserQueryRepository.getFollowers(userId));
  }

  @GetMapping("/{userId}/followees")
  public DataResponse<List<FollowUserResponse>> getFollowees(@PathVariable Long userId) {
    return DataResponse.ok(jpaUserQueryRepository.getFollowees(userId));
  }

  @GetMapping("/{userId}")
  public DataResponse<UserResponse> getUserProfile(@PathVariable Long userId) {
    return DataResponse.ok(userService.getUserProfile(userId));
  }

}
