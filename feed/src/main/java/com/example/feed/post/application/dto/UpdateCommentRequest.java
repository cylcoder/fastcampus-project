package com.example.feed.post.application.dto;

public record UpdateCommentRequest(
    Long userId,
    String content
) {

}
