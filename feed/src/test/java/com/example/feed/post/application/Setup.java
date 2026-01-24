package com.example.feed.post.application;

import static com.example.feed.fake.FakeObjectFactory.getCommentService;
import static com.example.feed.fake.FakeObjectFactory.getPostService;
import static com.example.feed.fake.FakeObjectFactory.getUserService;
import static com.example.feed.post.domain.content.PostStatus.PUBLIC;

import org.junit.jupiter.api.BeforeEach;
import com.example.feed.post.application.dto.CreateCommentRequest;
import com.example.feed.post.application.dto.CreatePostRequest;
import com.example.feed.post.domain.Post;
import com.example.feed.post.domain.comment.Comment;
import com.example.feed.user.application.UserService;
import com.example.feed.user.application.dto.CreateUserRequest;
import com.example.feed.user.domain.User;

public class Setup {

  public static final String CONTENT = "The quick brown fox jumps over the lazy dog.";

  public final UserService userService = getUserService();
  public final PostService postService = getPostService();
  public final CommentService commentService = getCommentService();

  public User user1;
  public User user2;
  public Post post;
  public Comment comment;

  @BeforeEach
  void setUp() {
    user1 = userService.createUser(new CreateUserRequest("user1", "url"));
    user2 = userService.createUser(new CreateUserRequest("user2", "url"));

    CreatePostRequest createPostRequest = new CreatePostRequest(user1.getId(), "Test Content", PUBLIC);
    post = postService.createPost(createPostRequest);

    CreateCommentRequest createCommentRequest = new CreateCommentRequest(post.getId(), user1.getId(), CONTENT);
    comment = commentService.createComment(createCommentRequest);
  }

}
