package com.example.feed.post.application.dto;

import com.example.feed.post.domain.content.PostStatus;

public record CreatePostRequest(
    Long userId,
    String content,
    PostStatus status
) {

}
