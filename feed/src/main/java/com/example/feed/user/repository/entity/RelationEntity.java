package com.example.feed.user.repository.entity;

import com.example.feed.common.repository.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "relation")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@IdClass(RelationIdEntity.class)
public class RelationEntity extends BaseTimeEntity {

  @Id
  private Long followerId;

  @Id
  private Long followeeId;

}
