package org.fastcampus.post.domain.comment;

import org.fastcampus.post.domain.Post;
import org.fastcampus.post.domain.content.CommentContent;
import org.fastcampus.post.domain.content.PostContent;
import org.fastcampus.user.domain.User;
import org.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    private final UserInfo userInfo = new UserInfo("John", "https://s3.amazonaws.com/john");
    private final User user = new User(1L, userInfo);
    private final User otherUser = new User(2L, userInfo);
    private final Post post = new Post(1L, user, new PostContent("Hello World"));
    private final Comment comment = new Comment(1L, post, user, new CommentContent("Hello World"));

    @Test
    void givenCommentCreated_WhenLike_ThenLikeCountShouldBe1() {
        // when
        comment.like(otherUser);

        // then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_WhenUserLikeHimself_ThenThrowError() {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentLiked_WhenUnlike_ThenLikeCountShouldBe0() {
        // given
        comment.like(otherUser);

        // when
        comment.unlike(otherUser);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_WhenUnlike_thenLikeCountShouldBe0() {
        // when
        comment.unlike(otherUser);

        // then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_WhenUpdateContent_thenContentShouldBeUpdated() {
        // given
        String updatedContent = "Updated Content";

        // when
        comment.updateComment(user, updatedContent);

        // then
        assertEquals(updatedContent, comment.getContent());
    }

    @Test
    void givenCommentCreated_WhenUpdateContentOver100_ThenThrowError() {
        // given
        String updatedContent = "#".repeat(101);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, updatedContent));
    }

}