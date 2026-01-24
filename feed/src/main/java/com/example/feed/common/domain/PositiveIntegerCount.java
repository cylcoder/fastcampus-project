package com.example.feed.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PositiveIntegerCount {

  private int count;

  public PositiveIntegerCount(int count) {
    this.count = count;
  }

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
