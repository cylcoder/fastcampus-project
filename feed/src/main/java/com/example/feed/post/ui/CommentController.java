package com.example.feed.post.ui;

import com.example.feed.common.ui.DataResponse;
import com.example.feed.common.ui.Response;
import com.example.feed.post.application.CommentService;
import com.example.feed.post.application.dto.CreateCommentRequest;
import com.example.feed.post.application.dto.LikeRequest;
import com.example.feed.post.application.dto.UpdateCommentRequest;
import com.example.feed.post.domain.comment.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public DataResponse<Long> createComment(@RequestBody CreateCommentRequest request) {
    Comment comment = commentService.createComment(request);
    return DataResponse.ok(comment.getId());
  }

  @PatchMapping("/{commentId}")
  public DataResponse<Long> updateComment(
      @PathVariable Long commentId,
      @RequestBody UpdateCommentRequest request
  ) {
    Comment comment = commentService.updateComment(commentId, request);
    return DataResponse.ok(comment.getId());
  }

  @PostMapping("/like")
  public Response likeComment(@RequestBody LikeRequest request) {
    commentService.likeComment(request);
    return Response.ok();
  }

  @PostMapping("/unlike")
  public Response unlikeComment(@RequestBody LikeRequest request) {
    commentService.unlikeComment(request);
    return Response.ok();
  }

}
