package com.example.feed.post.domain.content;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

  @Test
  void givenValidText_whenCreated_thenTextIsEqual() {
    // given
    String text = "The quick brown fox jumps over the lazy dog.";

    // when
    PostContent content = new PostContent(text);

    // then
    assertThat(content.getText()).isEqualTo(text);
  }

  @Test
  void givenTooLongText_whenCreate_thenThrowException() {
    // given
    String text = " ".repeat(501);

    // when & then
    assertThatThrownBy(() -> new PostContent(text))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = {"홽", "췗", "믵"})
  void givenComplexKoreanText_whenCreate_thenTextIsEqual(String text) {
    // when
    PostContent content = new PostContent(text);

    // then
    assertThat(content.getText()).isEqualTo(text);
  }

  @Test
  void givenTooShortText_whenCreate_thenThrowException() {
    // given
    String text = " ".repeat(4);

    // when & then
    assertThatThrownBy(() -> new PostContent(text))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @ParameterizedTest
  @NullAndEmptySource // null & ""
  void givenNullOrEmptyText_whenCreate_thenThrowException(String text) {
    assertThatThrownBy(() -> new PostContent(text))
        .isInstanceOf(IllegalArgumentException.class);
  }

}