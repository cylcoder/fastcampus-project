package org.fastcampus.post.domain;

import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.post.domain.content.PostPublicationState;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    private final UserInfo userInfo = new UserInfo("John", "https://s3.amazonaws.com/john");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = new Post(1L, user, new PostContent("Hello World"));

    @Test
    void givenPostCreated_WhenLike_ThenLikeCountShouldBe1() {
        // when
        post.like(otherUser);

        // then
        assertEquals(1, post.getLikeCount());
    }

    @Test
    void givenPostCreated_WhenUserLikeHimself_ThenThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post.like(user));
    }

    @Test
    void givenPostLiked_WhenUnlike_ThenLikeCountShouldBe0() {
        // given
        post.like(otherUser);

        // when
        post.unlike(otherUser);

        // then
        assertEquals(0, post.getLikeCount());
    }

    @Test
    void givenPostCreated_WhenUserUnlike_ThenThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post.unlike(user));
    }

    @Test
    void givenPostCreated_WhenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String updatedContent = "Updated Content";

        // when
        post.updatePost(user, updatedContent, PostPublicationState.PUBLIC);

        // then
        assertEquals(updatedContent, post.getContent());
    }

    @Test
    void givenPostCreated_WhenUserIsNotAuthor_thenThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> post.updatePost(otherUser, "Updated Content", PostPublicationState.PUBLIC));
    }

}