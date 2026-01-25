package com.example.feed.user.repository.jpa;

import com.example.feed.user.repository.entity.RelationEntity;
import com.example.feed.user.repository.entity.RelationIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRelationRepository extends JpaRepository<RelationEntity, RelationIdEntity> {

}
