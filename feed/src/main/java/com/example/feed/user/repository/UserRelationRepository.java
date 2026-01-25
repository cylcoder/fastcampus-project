package com.example.feed.user.repository;

import com.example.feed.user.application.interfaces.RelationRepository;
import com.example.feed.user.domain.User;
import com.example.feed.user.repository.entity.RelationEntity;
import com.example.feed.user.repository.entity.RelationIdEntity;
import com.example.feed.user.repository.entity.UserEntity;
import com.example.feed.user.repository.jpa.JpaRelationRepository;
import com.example.feed.user.repository.jpa.JpaUserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRelationRepository implements RelationRepository {

  private final JpaRelationRepository jpaRelationRepository;
  private final JpaUserRepository jpaUserRepository;

  @Override
  public boolean isFollowing(User follower, User followee) {
    RelationIdEntity relationIdEntity = new RelationIdEntity(follower.getId(), followee.getId());
    return jpaRelationRepository.existsById(relationIdEntity);
  }

  @Transactional
  @Override
  public void save(User follower, User followee) {
    RelationEntity relationEntity = new RelationEntity(follower.getId(), followee.getId());
    jpaRelationRepository.save(relationEntity);
    jpaUserRepository.saveAll(List.of(UserEntity.from(follower), UserEntity.from(followee)));
  }

  @Transactional
  @Override
  public void delete(User follower, User followee) {
    RelationIdEntity relationIdEntity = new RelationIdEntity(follower.getId(), followee.getId());
    jpaRelationRepository.deleteById(relationIdEntity);
    jpaUserRepository.saveAll(List.of(UserEntity.from(follower), UserEntity.from(followee)));
  }

}
