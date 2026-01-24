package com.example.feed.post.application;

import static org.assertj.core.api.Assertions.assertThat;
import static com.example.feed.post.domain.content.PostStatus.PUBLIC;

import org.junit.jupiter.api.Test;
import com.example.feed.post.application.dto.LikeRequest;
import com.example.feed.post.application.dto.UpdatePostRequest;
import com.example.feed.post.domain.Post;

class PostServiceTest extends Setup {

  @Test
  void givenCreatePostRequest_whenCreatePost_thenPostIsCreated() {
    Post foundPost = postService.getPost(post.getId());
    assertThat(post).isEqualTo(foundPost);
  }

  @Test
  void givenPost_whenUpdate_thenPostIsUpdated() {
    // when
    String newContent = "The lazy dog finally woke up and barked at the fox.";
    UpdatePostRequest updatePostRequest = new UpdatePostRequest(post.getId(), user1.getId(), newContent, PUBLIC);
    postService.updatePost(updatePostRequest);

    // then
    Post updatedPost = postService.getPost(post.getId());
    assertThat(updatedPost)
        .returns(newContent, Post::getContent)
        .returns(PUBLIC, Post::getStatus);
  }

  @Test
  void givenPost_whenLike_thenLikeCountIncreased() {
    // when
    LikeRequest request = new LikeRequest(post.getId(), user2.getId());
    postService.likePost(request);

    // then
    Post likedPost = postService.getPost(post.getId());
    assertThat(likedPost.getLikeCount()).isEqualTo(1);
  }

  @Test
  void givenPostLiked_whenUnlike_thenLikeCountDecreased() {
    // given
    LikeRequest likeRequest = new LikeRequest(post.getId(), user2.getId());
    postService.likePost(likeRequest);

    // when
    LikeRequest unlikeRequest = new LikeRequest(post.getId(), user2.getId());
    postService.unlikePost(unlikeRequest);

    // then
    Post unlikedPost = postService.getPost(post.getId());
    assertThat(unlikedPost.getLikeCount()).isZero();
  }

}