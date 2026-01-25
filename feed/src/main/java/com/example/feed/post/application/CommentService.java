package com.example.feed.post.application;

import lombok.RequiredArgsConstructor;
import com.example.feed.post.application.dto.CreateCommentRequest;
import com.example.feed.post.application.dto.LikeRequest;
import com.example.feed.post.application.dto.UpdateCommentRequest;
import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.user.application.UserService;
import com.example.feed.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final UserService userService;
  private final PostService postService;
  private final CommentRepository commentRepository;
  private final LikeRepository likeRepository;

  public Comment getComment(Long id) {
    return commentRepository.findById(id);
  }

  public Comment createComment(CreateCommentRequest request) {
    Post post = postService.getPost(request.postId());
    User author = userService.getUser(request.userId());
    Comment comment = new Comment(null, post, author, request.content());
    return commentRepository.save(comment);
  }

  public Comment updateComment(Long commentId, UpdateCommentRequest request) {
    Comment comment = getComment(commentId);
    User author = userService.getUser(request.userId());
    comment.update(author, request.content());
    return commentRepository.save(comment);
  }

  public void likeComment(LikeRequest request) {
    Comment comment = getComment(request.targetId());
    User user = userService.getUser(request.userId());

    if (likeRepository.existsByTargetAndUser(comment, user)) {
      throw new IllegalArgumentException();
    }

    comment.like(user);
    likeRepository.like(comment, user);
  }

  public void unlikeComment(LikeRequest request) {
    Comment comment = getComment(request.targetId());
    User user = userService.getUser(request.userId());

    if (!likeRepository.existsByTargetAndUser(comment, user)) {
      throw new IllegalArgumentException();
    }

    comment.unlike(user);
    likeRepository.unlike(comment, user);
  }

}
