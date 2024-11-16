package org.fastcampus.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CommentContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent() {
        // given
        String contentText = "Hello World";

        // when
        CommentContent commentContent = new CommentContent(contentText);

        // then
        assertEquals(contentText, commentContent.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError() {
        // given
        String contentText = "#".repeat(101);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @ValueSource(strings = {"갉", "낡", "닭"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord) {
        // given
        String contentText = koreanWord.repeat(101);

        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(contentText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value) {
        // when
        // then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(value));
    }

}