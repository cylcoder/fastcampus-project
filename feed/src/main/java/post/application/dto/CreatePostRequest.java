package post.application.dto;

import post.domain.content.PostStatus;

public record CreatePostRequest(
    Long userId,
    String content,
    PostStatus status
) {

}
