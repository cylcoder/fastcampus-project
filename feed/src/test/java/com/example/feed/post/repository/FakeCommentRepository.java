package com.example.feed.post.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.domain.comment.Comment;

public class FakeCommentRepository implements CommentRepository {

  private final Map<Long, Comment> store = new HashMap<>();

  @Override
  public Comment save(Comment comment) {
    if (comment.getId() == null) {
      comment = new Comment(store.size() + 1L, comment.getPost(), comment.getAuthor(), comment.getContent());
    }
    store.put(comment.getId(), comment);
    return comment;
  }

  @Override
  public Optional<Comment> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

}
