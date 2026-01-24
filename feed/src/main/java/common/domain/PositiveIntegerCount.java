package common.domain;

public class PositiveIntegerCount {

  private int count;

  public void increase() {
    count++;
  }

  public void decrease() {
    if (count == 0) {
      throw new IllegalArgumentException();
    }
    count--;
  }

}
