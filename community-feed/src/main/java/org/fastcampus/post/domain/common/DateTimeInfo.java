package org.fastcampus.post.domain.common;

import java.time.LocalDateTime;

public class DateTimeInfo {

    private boolean isEdited;
    private LocalDateTime dateTime;

    public DateTimeInfo() {
        isEdited = false;
        dateTime = LocalDateTime.now();
    }

    public boolean isEdited() {
        return isEdited;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void updateEditDateTime() {
        isEdited = true;
        dateTime = LocalDateTime.now();
    }

}
