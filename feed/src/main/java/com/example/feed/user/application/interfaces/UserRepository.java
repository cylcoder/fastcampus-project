package com.example.feed.user.application.interfaces;

import com.example.feed.user.domain.User;

public interface UserRepository {

  User save(User user);

  User findById(Long id);

}
