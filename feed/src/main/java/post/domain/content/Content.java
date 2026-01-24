package post.domain.content;

import lombok.Getter;
import post.domain.common.DateTimeInfo;

@Getter
public abstract class Content {

  String text;
  final DateTimeInfo dateTimeInfo;

  Content(String text) {
    validateText(text);
    this.text = text;
    this.dateTimeInfo = new DateTimeInfo();
  }

  abstract void validateText(String text);

  public void update(String text) {
    validateText(text);
    this.text = text;
  }

}
