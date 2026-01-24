package com.example.feed.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class InfoTest {

  @Test
  void givenNameAndProfileImageUrl_whenCreated_thenThrowNothing() {
    // given
    String name = "foo";
    String url = "bar";

    // when
    Info info = new Info(name, url);

    // then
    assertThat(info)
        .returns(name, Info::getName)
        .returns(url, Info::getProfileImageUrl);
  }

  @Test
  void givenNameBlankAndProfileImageUrl_whenCreated_thenThrowException() {
    // given
    String name = "";
    String url = "";

    // when & then
    assertThatThrownBy(() -> new Info(name, url)).isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Name cannot be null or blank.");
  }

}