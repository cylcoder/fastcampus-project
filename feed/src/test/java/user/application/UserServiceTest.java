package user.application;

import static fake.FakeObjectFactory.getUserService;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import user.application.dto.CreateUserRequest;
import user.domain.User;

class UserServiceTest {

  private final UserService userService = getUserService();

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