package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.comment.Comment;

public interface CommentRepository {

  Comment save(Comment comment);

  Comment findById(Long id);

}
