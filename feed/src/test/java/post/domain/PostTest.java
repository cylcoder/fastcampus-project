package post.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import post.domain.content.PostContent;
import post.domain.content.PostStatus;
import user.domain.Info;
import user.domain.User;

class PostTest {

  private final Info info1 = new Info("user1", "url1");
  private final User user1 = new User(1L, info1);

  private final Info info2 = new Info("user2", "url2");
  private final User user2 = new User(2L, info2);

  private final Post post = new Post(1L, user1, new PostContent("text1"));

  @Test
  void givenPostCreated_whenAnotherUserLikes_thenLikeCountIncreased() {
    // when
    post.like(user2);

    // then
    assertThat(post.getLikeCount()).isEqualTo(1);
  }

  @Test
  void givenPostCreated_whenLikeOneself_thenThrowsException() {
    // when & then
    assertThatThrownBy(() -> post.like(user1))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenPostLiked_whenAnotherUserUnlikes_thenLikeCountDecreased() {
    // given
    post.like(user2);

    // when
    post.unlike(user2);

    // then
    assertThat(post.getLikeCount()).isZero();
  }

  @Test
  void givenPostCreated_whenAnotherUserUnlikes_thenThrowsException() {
    // when & then
    assertThatThrownBy(() -> post.unlike(user2))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  void givenPostCreated_whenUpdateContent_thenContentIsUpdated() {
    // when
    String text = "The quick brown fox jumps over the lazy dog.";
    post.update(user1, text, PostStatus.PRIVATE);

    // then
    assertThat(post.getContent()).isEqualTo(text);
  }

  @Test
  void givenPostCreated_whenAnotherUserTryToUpdateContent_thenThrowsException() {
    // when & then
    assertThatThrownBy(() -> post.update(user2, "The quick brown fox jumps over the lazy dog.", PostStatus.PRIVATE))
        .isInstanceOf(IllegalArgumentException.class);
  }

}
