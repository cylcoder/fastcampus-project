package com.example.feed.post.repository;

import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.domain.comment.Comment;
import java.util.HashMap;
import java.util.Map;

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
  public Comment findById(Long id) {
    return store.get(id);
  }

}
