package com.example.feed.post.repository.jpa;


import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.post.repository.entity.comment.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepository {

  private final JpaCommentRepository jpaCommentRepository;

  @Override
  public Comment save(Comment comment) {
    CommentEntity commentEntity = CommentEntity.from(comment);
    jpaCommentRepository.save(commentEntity);
    return commentEntity.toComment();
  }

  @Override
  public Comment findById(Long id) {
    CommentEntity commentEntity = jpaCommentRepository.findById(id).orElseThrow();
    return commentEntity.toComment();
  }

}
