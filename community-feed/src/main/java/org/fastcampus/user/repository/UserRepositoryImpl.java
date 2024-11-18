package org.fastcampus.user.repository;

import lombok.RequiredArgsConstructor;
import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.user.application.interfaces.UserRepository;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.repository.entity.UserEntity;
import org.fastcampus.user.repository.jpa.JpaUserRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private JpaUserRepository<TimeBaseEntity, Number> jpaUserRepository;

    @Override
    public User save(User user) {
        return jpaUserRepository.save(new UserEntity(user)).toUser();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id).orElseThrow(IllegalArgumentException::new).toUser();
    }

}
