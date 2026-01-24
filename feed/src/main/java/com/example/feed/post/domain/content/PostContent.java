package com.example.feed.post.domain.content;

public class PostContent extends Content {

  private static final int MIN_LENGTH = 5;
  private static final int MAX_LENGTH = 500;

  public PostContent(String text) {
    super(text);
  }

  @Override
  void validateText(String text) {
    if (text == null || text.length() < MIN_LENGTH || text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException();
    }
  }

}
