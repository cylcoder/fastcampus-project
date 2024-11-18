package org.fastcampus.user.repository.jpa;

import org.fastcampus.common.repository.entity.TimeBaseEntity;
import org.fastcampus.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository<U extends TimeBaseEntity, L extends Number> extends JpaRepository<UserEntity, Long> {

}
