package user.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import user.application.dto.CreateUserRequest;
import user.application.interfaces.UserRepository;
import user.domain.User;
import user.repository.FakeUserRepository;

class UserServiceTest {

  private final UserRepository userRepository = new FakeUserRepository();
  private final UserService userService = new UserService(userRepository);

  @Test
  void givenCreateUserRequest_whenCreateUser_thenCanFindUser() {
    // given
    CreateUserRequest request = new CreateUserRequest("foo", "bar");

    // when
    User savedUser = userService.createUser(request);

    // then
    User foundUser = userService.getUser(savedUser.getId());
    assertThat(savedUser).isEqualTo(foundUser);
  }

}