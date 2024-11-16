package org.fastcampus.post.domain.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStateArsUpdated() {
        // given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime dateTime = dateTimeInfo.getDateTime();

        // when
        dateTimeInfo.updateEditDateTime();

        // then
        assertTrue(dateTimeInfo.isEdited());
        assertNotEquals(dateTime, dateTimeInfo.getDateTime());
    }

}