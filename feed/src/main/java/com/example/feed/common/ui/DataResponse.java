package com.example.feed.common.ui;

import com.example.feed.common.domain.exception.ErrorCode;

public record DataResponse<T>(int code, String message, T data) {

  public static <T> DataResponse<T> ok(T data) {
    return new DataResponse<>(200, "OK", data);
  }

}
