package com.example.feed.post.repository.entity.post;

import com.example.feed.post.domain.content.PostStatus;
import jakarta.persistence.AttributeConverter;

public class PostStatusConverter implements AttributeConverter<PostStatus, String> {

  @Override
  public String convertToDatabaseColumn(PostStatus postStatus) {
    return postStatus.name();
  }

  @Override
  public PostStatus convertToEntityAttribute(String name) {
    return PostStatus.valueOf(name);
  }

}
