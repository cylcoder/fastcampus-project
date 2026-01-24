package fake;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import post.application.CommentService;
import post.application.PostService;
import post.application.interfaces.CommentRepository;
import post.application.interfaces.LikeRepository;
import post.application.interfaces.PostRepository;
import post.repository.FakeCommentRepository;
import post.repository.FakeLikeRepository;
import post.repository.FakePostRepository;
import user.application.UserRelationService;
import user.application.UserService;
import user.application.interfaces.UserRelationRepository;
import user.application.interfaces.UserRepository;
import user.repository.FakeUserRelationRepository;
import user.repository.FakeUserRepository;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakeObjectFactory {

  private static final UserRepository userRepository = new FakeUserRepository();
  private static final PostRepository postRepository = new FakePostRepository();
  private static final CommentRepository commentRepository = new FakeCommentRepository();
  private static final LikeRepository likeRepository = new FakeLikeRepository();
  private static final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();

  private static final UserService userService = new UserService(userRepository);
  private static final PostService postService = new PostService(userService, postRepository, likeRepository);
  private static final CommentService commentService = new CommentService(userService, postService, commentRepository, likeRepository);
  private static final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

  public static UserService getUserService() {
    return userService;
  }

  public static PostService getPostService() {
    return postService;
  }

  public static CommentService getCommentService() {
    return commentService;
  }

  public static UserRelationService getUserRelationService() {
    return userRelationService;
  }

}
