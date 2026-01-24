package com.example.feed.post.domain.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class DateTimeInfoTest {

  @Test
  void givenInfo_whenUpdateDateTime_thenFlagAndTimeUpdated() {
    // given
    DateTimeInfo info = new DateTimeInfo();
    LocalDateTime createdTime = info.getDateTime();

    // when
    info.updateDateTime();

    // then
    assertThat(info.isEdited()).isTrue();
    assertThat(info.getDateTime()).isNotEqualTo(createdTime);
  }

}