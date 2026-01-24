package com.example.feed.post.application.interfaces;

import java.util.Optional;
import com.example.feed.post.domain.comment.Comment;

public interface CommentRepository {

  Comment save(Comment comment);

  Optional<Comment> findById(Long id);

}
