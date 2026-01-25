package com.example.feed.fake;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import com.example.feed.post.application.CommentService;
import com.example.feed.post.application.PostService;
import com.example.feed.post.application.interfaces.CommentRepository;
import com.example.feed.post.application.interfaces.LikeRepository;
import com.example.feed.post.application.interfaces.PostRepository;
import com.example.feed.post.repository.FakeCommentRepository;
import com.example.feed.post.repository.FakeLikeRepository;
import com.example.feed.post.repository.FakePostRepository;
import com.example.feed.user.application.RelationService;
import com.example.feed.user.application.UserService;
import com.example.feed.user.application.interfaces.RelationRepository;
import com.example.feed.user.application.interfaces.UserRepository;
import com.example.feed.user.repository.FakeRelationRepository;
import com.example.feed.user.repository.FakeUserRepository;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FakeObjectFactory {

  private static final UserRepository userRepository = new FakeUserRepository();
  private static final PostRepository postRepository = new FakePostRepository();
  private static final CommentRepository commentRepository = new FakeCommentRepository();
  private static final LikeRepository likeRepository = new FakeLikeRepository();
  private static final RelationRepository RELATION_REPOSITORY = new FakeRelationRepository();

  private static final UserService userService = new UserService(userRepository);
  private static final PostService postService = new PostService(userService, postRepository, likeRepository);
  private static final CommentService commentService = new CommentService(userService, postService, commentRepository, likeRepository);
  private static final RelationService RELATION_SERVICE = new RelationService(userService,
      RELATION_REPOSITORY);

  public static UserService getUserService() {
    return userService;
  }

  public static PostService getPostService() {
    return postService;
  }

  public static CommentService getCommentService() {
    return commentService;
  }

  public static RelationService getRELATION_SERVICE() {
    return RELATION_SERVICE;
  }

}
