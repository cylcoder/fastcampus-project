package com.example.feed.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Info {

  private final String name;
  private final String profileImageUrl;

  public Info(String name, String profileImageUrl) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Name cannot be null or blank.");
    }
    this.name = name;
    this.profileImageUrl = profileImageUrl;
  }

}
