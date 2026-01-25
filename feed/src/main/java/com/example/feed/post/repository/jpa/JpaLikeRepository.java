package com.example.feed.post.repository.jpa;

import com.example.feed.post.repository.entity.like.LikeEntity;
import com.example.feed.post.repository.entity.like.LikeIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LikeIdEntity> {

}
