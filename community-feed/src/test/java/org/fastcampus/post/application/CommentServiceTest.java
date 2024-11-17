package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.application.dto.UpdateCommentRequestDto;
import org.fastcampus.post.domain.comment.Comment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommentServiceTest extends PostApplicationTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment() {
        // when
        Comment comment = commentService.createComment(commentRequestDto);

        // then
         Comment savedComment = commentService.getComment(comment.getId());
        assertEquals(comment, savedComment);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);

        // when
        UpdateCommentRequestDto dto = new UpdateCommentRequestDto(savedComment.getId(), user.getId(), "updated content");
        Comment updatedComment = commentService.updateComment(dto);

        // then
        assertEquals(savedComment.getId(), updatedComment.getId());
        assertEquals(savedComment.getAuthor(), updatedComment.getAuthor());
        assertEquals(savedComment.getContent(), updatedComment.getContent());
    }

    @Test
    void givenComment_whenLike_thenCommentLiked() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);

        // when
        commentService.likeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // then
        Comment likedComment = commentService.getComment(savedComment.getId());
        assertEquals(1, likedComment.getLikeCount());
    }

    @Test
    void givenCommentLiked_whenUnlike_thenReturnCommentWithoutLike() {
        // given
        Comment savedComment = commentService.createComment(commentRequestDto);
        commentService.likeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // when
        commentService.unlikeComment(new LikeRequestDto(savedComment.getId(), otherUser.getId()));

        // then
        Comment unlikedComment = commentService.getComment(savedComment.getId());
        assertEquals(0, unlikedComment.getLikeCount());
    }

}