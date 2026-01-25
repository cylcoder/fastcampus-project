package com.example.feed.post.ui;

import com.example.feed.common.ui.DataResponse;
import com.example.feed.common.ui.Response;
import com.example.feed.post.application.PostService;
import com.example.feed.post.application.dto.CreatePostRequest;
import com.example.feed.post.application.dto.LikeRequest;
import com.example.feed.post.application.dto.UpdatePostRequest;
import com.example.feed.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public DataResponse<Long> createPost(@RequestBody CreatePostRequest request) {
    Post post = postService.createPost(request);
    return DataResponse.ok(post.getId());
  }

  @PatchMapping("/{postId}")
  public DataResponse<Long> updatePost(@PathVariable Long postId, @RequestBody UpdatePostRequest request) {
    Post post = postService.updatePost(postId, request);
    return DataResponse.ok(post.getId());
  }

  @PostMapping("/like")
  public Response likePost(@RequestBody LikeRequest request) {
    postService.likePost(request);
    return Response.ok();
  }

  @PostMapping("/unlike")
  public Response unlikePost(@RequestBody LikeRequest request) {
    postService.unlikePost(request);
    return Response.ok();
  }

}
