package com.example.feed.post.application.interfaces;

import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.user.domain.User;

public interface LikeRepository {

  boolean existsByTargetAndUser(Post post, User user);
  boolean existsByTargetAndUser(Comment comment, User user);

  void like(Post post, User user);
  void like(Comment comment, User user);

  void unlike(Post post, User user);
  void unlike(Comment comment, User user);

}
