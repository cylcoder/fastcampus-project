package common.domain;

import lombok.Getter;

@Getter
public class PositiveIntegerCount {

  private int count;

  public void increase() {
    count++;
  }

  public void decrease() {
    if (count <= 0) {
      throw new IllegalArgumentException("Count cannot be negative.");
    }
    count--;
  }

}
