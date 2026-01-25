package com.example.feed.user.repository.jpa;

import com.example.feed.user.application.dto.FollowUserResponse;
import com.example.feed.user.repository.entity.QRelationEntity;
import com.example.feed.user.repository.entity.QUserEntity;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuerydslUserQueryRepository {

  private final JPAQueryFactory jpaQueryFactory;
  private static final QUserEntity user = QUserEntity.userEntity;
  private static final QRelationEntity relation = QRelationEntity.relationEntity;

  public List<FollowUserResponse> getFollowers(Long userId, Long lastFollowerId) {
    return jpaQueryFactory.select(
          Projections.constructor(
              FollowUserResponse.class,
              user.name,
              user.profileImageUrl
          )
        )
        .from(relation)
        .join(user).on(relation.followerId.eq(user.id))
        .where(relation.followeeId.eq(userId), ltLastFollowerId(lastFollowerId))
        .orderBy(user.id.desc())
        .limit(20)
        .fetch();
  }

  private BooleanExpression ltLastFollowerId(Long lastFollowerId) {
    if (lastFollowerId == null) {
      return null;
    }
    return user.id.lt(lastFollowerId);
  }

}
