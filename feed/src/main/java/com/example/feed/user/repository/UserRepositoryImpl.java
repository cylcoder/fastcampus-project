package com.example.feed.user.repository;

import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.domain.User;
import com.example.feed.user.repository.entity.UserEntity;
import com.example.feed.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JpaUserRepository jpaUserRepository;

  @Override
  public User save(User user) {
    UserEntity userEntity = UserEntity.from(user);
    jpaUserRepository.save(userEntity);
    return userEntity.toUser();
  }

  @Override
  public User findById(Long id) {
    return jpaUserRepository.findById(id)
        .orElseThrow()
        .toUser();
  }

}
