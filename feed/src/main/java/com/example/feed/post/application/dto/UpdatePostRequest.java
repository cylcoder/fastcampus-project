package com.example.feed.post.application.dto;

import com.example.feed.post.domain.content.PostStatus;

public record UpdatePostRequest(
    Long postId,
    Long userId,
    String content,
    PostStatus status
    ) {

}
