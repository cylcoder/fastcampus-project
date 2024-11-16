package org.fastcampus.post.domain.content;

import org.fastcampus.post.domain.common.DateTimeInfo;

public abstract class Content {

    private String contentText;
    private final DateTimeInfo dateTimeInfo;

    protected Content(String contentText) {
        checkText(contentText);
        this.contentText = contentText;
        dateTimeInfo = new DateTimeInfo();
    }

    public String getContentText() {
        return contentText;
    }

    public abstract void checkText(String contentText);

    public void updateContent(String updateContent) {
        checkText(updateContent);
        contentText = updateContent;
        dateTimeInfo.updateEditDateTime();
    }

}
