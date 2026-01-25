package com.example.feed.post.repository;

import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.repository.entity.comment.CommentEntity;
import com.example.feed.post.repository.entity.like.LikeEntity;
import com.example.feed.post.repository.entity.post.PostEntity;
import com.example.feed.post.repository.jpa.JpaCommentRepository;
import com.example.feed.post.repository.jpa.JpaLikeRepository;
import com.example.feed.post.repository.jpa.JpaPostRepository;
import com.example.feed.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepositoryImp implements LikeRepository {

  private final JpaLikeRepository jpaLikeRepository;
  private final JpaCommentRepository jpaCommentRepository;
  private final JpaPostRepository jpaPostRepository;

  @Override
  public boolean existsByTargetAndUser(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  public boolean existsByTargetAndUser(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    return jpaLikeRepository.existsById(likeEntity.getId());
  }

  @Override
  public void like(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    jpaLikeRepository.save(likeEntity);
    PostEntity postEntity = PostEntity.from(post);
    jpaPostRepository.save(postEntity);
  }

  @Override
  public void like(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    jpaLikeRepository.save(likeEntity);
    CommentEntity commentEntity = CommentEntity.from(comment);
    jpaCommentRepository.save(commentEntity);
  }

  @Override
  public void unlike(Post post, User user) {
    LikeEntity likeEntity = new LikeEntity(post, user);
    jpaLikeRepository.delete(likeEntity);
    PostEntity postEntity = PostEntity.from(post);
    jpaPostRepository.save(postEntity);
  }

  @Override
  public void unlike(Comment comment, User user) {
    LikeEntity likeEntity = new LikeEntity(comment, user);
    jpaLikeRepository.delete(likeEntity);
    CommentEntity commentEntity = CommentEntity.from(comment);
    jpaCommentRepository.save(commentEntity);
  }
}
