package post.application.dto;

import post.domain.content.PostStatus;

public record UpdatePostRequest(
    Long postId,
    Long userId,
    String content,
    PostStatus status
    ) {

}
