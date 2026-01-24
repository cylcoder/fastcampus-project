package post.domain.content;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CommentContentTest {

  @Test
  void givenValidText_whenCreate_thenTextIsEqual() {
    // given
    String text = "The quick brown fox jumps over the lazy dog.";

    // when
    CommentContent content = new CommentContent(text);

    // then
    assertThat(content.getText()).isEqualTo(text);
  }

  @Test
  void givenInvalidText_whenCreate_thenThrowsException() {
    // given
    String text = " ".repeat(101);

    // when & then
    assertThatThrownBy(() -> new CommentContent(text))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"홽", "췗", "믵"})
  void givenComplexKoreanText_whenCreate_thenCreated(String text) {
    // when
    CommentContent content = new CommentContent(text);

    // then
    assertThat(content.getText()).isEqualTo(text);
  }

  @ParameterizedTest
  @NullAndEmptySource
  void givenNullOrEmptyText_whenCreate_thenThrowException(String text) {
    assertThatThrownBy(() -> new CommentContent(text))
        .isInstanceOf(IllegalArgumentException.class);
  }

}