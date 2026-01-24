package com.example.feed.post.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import com.example.feed.post.domain.comment.Comment;

class CommentServiceTest extends Setup {

  @Test
  void givenCreateCommentRequest_whenCreateComment_thenCommentIsCreated() {
    assertThat(comment)
        .returns(CONTENT, Comment::getContent)
        .returns(user1, Comment::getAuthor)
        .returns(post, Comment::getPost);
  }

}