package com.example.feed.post.application.dto;

public record UpdateCommentRequest(
    Long commentId,
    Long userId,
    String content
) {

}
