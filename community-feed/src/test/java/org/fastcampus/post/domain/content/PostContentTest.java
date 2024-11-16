package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String contentText = "Hello World";

        // when
        PostContent postContent = new PostContent(contentText);

        // then
        assertEquals(contentText, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        // given
        String contentText = "#".repeat(501);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"갉", "낡", "닭"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
        // given
        String contentText = koreanWord.repeat(501);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError() {
        // given
        String contentText = "#".repeat(4);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(contentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value) {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdatedContent() {
        // given
        String contentText = "Hello World";
        PostContent postContent = new PostContent(contentText);
        String updateContent = "Hello World!";

        // when
        postContent.updateContent(updateContent);

        // then
        assertEquals(updateContent, postContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError() {
        // given
        String contentText = "Hello World";
        PostContent postContent = new PostContent(contentText);
        String updateContent = "#".repeat(501);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updateContent));
    }

    @ParameterizedTest
    @ValueSource(strings = {"갉", "낡", "닭"})
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord) {
        // given
        String contentText = "Hello World";
        PostContent postContent = new PostContent(contentText);
        String updateContent = koreanWord.repeat(501);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updateContent));
    }

    @Test
    void givenContentLengthIsUnder_whenUpdated_thenThrowError() {
        // given
        String contentText = "Hello World";
        PostContent postContent = new PostContent(contentText);
        String updateContent = "#".repeat(4);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(updateContent));
    }

}