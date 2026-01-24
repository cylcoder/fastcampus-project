package post.domain.comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import post.domain.Post;
import post.domain.content.CommentContent;
import post.domain.content.PostContent;
import user.domain.Info;
import user.domain.User;

class CommentTest {

  private static final String TEXT = "The quick brown fox jumps over the lazy dog.";

  private final Info info1 = new Info("user1", "url1");
  private final User user1 = new User(1L, info1);

  private final Info info2 = new Info("user2", "url2");
  private final User user2 = new User(2L, info2);

  private final Post post = new Post(1L, user1, new PostContent(TEXT));
  private final Comment comment = new Comment(1L, post, user1, new CommentContent(TEXT));

  @Test
  void givenCommentCreated_whenAnotherUserLikes_thenLikeCountIncreased() {
    // when
    comment.like(user2);

    // then
    assertThat(comment.getLikeCount()).isEqualTo(1);
  }

  @Test
  void givenCommentCreated_whenLikeOneself_thenThrowsException() {
    assertThatThrownBy(() -> comment.like(user1))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenCommentLiked_whenAnotherUserUnlikes_thenLikeCountDecreased() {
    // given
    comment.like(user2);

    // when
    comment.unlike(user2);

    // then
    assertThat(comment.getLikeCount()).isZero();
  }

  @Test
  void givenTooLongText_whenCreateComment_thenThrowsException() {
    // given
    String text = " ".repeat(101);

    // when & then
    assertThatThrownBy(() -> new CommentContent(text)).isInstanceOf(IllegalArgumentException.class);
  }

}