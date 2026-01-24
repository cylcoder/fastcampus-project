package post.application;

import static fake.FakeObjectFactory.getCommentService;
import static fake.FakeObjectFactory.getPostService;
import static fake.FakeObjectFactory.getUserService;
import static post.domain.content.PostStatus.PUBLIC;

import org.junit.jupiter.api.BeforeEach;
import post.application.dto.CreateCommentRequest;
import post.application.dto.CreatePostRequest;
import post.domain.Post;
import post.domain.comment.Comment;
import user.application.UserService;
import user.application.dto.CreateUserRequest;
import user.domain.User;

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
