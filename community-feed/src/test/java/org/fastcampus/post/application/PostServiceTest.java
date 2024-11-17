package org.fastcampus.post.application;

import org.fastcampus.post.application.dto.LikeRequestDto;
import org.fastcampus.post.domain.Post;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostServiceTest extends PostApplicationTestTemplate{

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost() {
        // when
        Post post = postService.createPost(postRequestDto);

        // then
        Post savedPost = postService.getPost(post.getId());
        assertEquals(post, savedPost);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatedPost() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        Post updatedPost = postService.updatePost(savedPost.getId(), postRequestDto);

        // then
        assertEquals(savedPost.getId(), updatedPost.getId());
        assertEquals(savedPost.getAuthor(), updatedPost.getAuthor());
        assertEquals(savedPost.getContent(), updatedPost.getContent());
    }

    @Test
    void givenCreatePost_whenLike_thenPostLiked() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post likedPost = postService.getPost(savedPost.getId());
        assertEquals(1, likedPost.getLikeCount());
    }

    @Test
    void givenCreatePostLiked_whenUnliked_thenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(postRequestDto);
        postService.likePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // when
        postService.unlikePost(new LikeRequestDto(savedPost.getId(), otherUser.getId()));

        // then
        Post unlikedPost = postService.getPost(savedPost.getId());
        assertEquals(0, unlikedPost.getLikeCount());
    }

    @Test
    void givenCreatedPost_whenUnliked_thenThrowError() {
        // given
        Post savedPost = postService.createPost(postRequestDto);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postService.unlikePost(new LikeRequestDto(savedPost.getId(), otherUser.getId())));
    }

}