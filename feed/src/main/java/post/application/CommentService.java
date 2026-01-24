package post.application;

import lombok.RequiredArgsConstructor;
import post.application.dto.CreateCommentRequest;
import post.application.dto.LikeRequest;
import post.application.dto.UpdateCommentRequest;
import post.application.interfaces.CommentRepository;
import post.application.interfaces.LikeRepository;
import post.domain.Post;
import post.domain.comment.Comment;
import user.application.UserService;
import user.domain.User;

@RequiredArgsConstructor
public class CommentService {

  private final UserService userService;
  private final PostService postService;
  private final CommentRepository commentRepository;
  private final LikeRepository likeRepository;

  public Comment getComment(Long id) {
    return commentRepository.findById(id).orElseThrow();
  }

  public Comment createComment(CreateCommentRequest request) {
    Post post = postService.getPost(request.postId());
    User author = userService.getUser(request.userId());
    Comment comment = new Comment(null, post, author, request.content());
    return commentRepository.save(comment);
  }

  public Comment updateComment(UpdateCommentRequest request) {
    Comment comment = getComment(request.commentId());
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
