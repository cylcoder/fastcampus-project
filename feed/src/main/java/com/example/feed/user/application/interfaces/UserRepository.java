package com.example.feed.user.application.interfaces;

import java.util.Optional;
import com.example.feed.user.domain.User;

public interface UserRepository {

  User save(User user);

  Optional<User> findById(Long id);

}
