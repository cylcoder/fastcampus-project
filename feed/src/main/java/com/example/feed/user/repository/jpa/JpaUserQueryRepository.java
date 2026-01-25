package com.example.feed.user.repository.jpa;

import com.example.feed.user.application.dto.FollowUserResponse;
import com.example.feed.user.repository.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaUserQueryRepository extends JpaRepository<UserEntity, Long> {

  @Query("SELECT new com.example.feed.user.application.dto.FollowUserResponse(u.name, u.profileImageUrl) "
      + "FROM RelationEntity ur "
      + "JOIN UserEntity u ON ur.followerId = u.id "
      + "WHERE ur.followeeId = :userId")
  List<FollowUserResponse> getFollowers(Long userId);

  @Query("SELECT new com.example.feed.user.application.dto.FollowUserResponse(u.name, u.profileImageUrl) "
      + "FROM RelationEntity ur "
      + "JOIN UserEntity u ON ur.followeeId = u.id "
      + "WHERE ur.followerId = :userId")
  List<FollowUserResponse> getFollowees(Long userId);

}
