package com.example.feed.user.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.User;

public class FakeUserRepository implements UserRepository {

  private final Map<Long, User> store = new HashMap<>();

  @Override
  public User save(User user) {
    if (user.getId() == null) {
      Long id = (long) (store.size() + 1);
      user = new User(id, user.getInfo());
    }

    store.put(user.getId(), user);
    return user;
  }

  @Override
  public User findById(Long id) {
    return store.get(id);
  }

}
