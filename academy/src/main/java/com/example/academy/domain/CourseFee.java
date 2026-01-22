package com.example.academy.domain;

public class CourseFee {

  private int fee;

  public CourseFee(int fee) {
    this.fee = fee;
  }

  public void changeFee(int fee) {
    checkFee(fee);
    this.fee = fee;
  }

  private void checkFee(int fee) {
    if (fee < 0) {
      throw new IllegalArgumentException();
    }
  }

  public int getFee() {
    return fee;
  }
}
