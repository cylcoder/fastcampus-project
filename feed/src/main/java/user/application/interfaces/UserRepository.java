package user.application.interfaces;

import java.util.Optional;
import user.domain.User;

public interface UserRepository {

  User save(User user);

  Optional<User> findById(Long id);

}
