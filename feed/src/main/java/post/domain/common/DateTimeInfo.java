package post.domain.common;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class DateTimeInfo {

  private boolean isEdited;
  private LocalDateTime dateTime;

  public DateTimeInfo() {
    this.dateTime = now();
  }

  public void updateDateTime() {
    isEdited = true;
    dateTime = now();
  }

}
