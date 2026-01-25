package com.example.feed.common.ui;

import com.example.feed.common.domain.exception.ErrorCode;

public record Response(int code, String message) {

  public static Response error(ErrorCode errorCode) {
    return new Response(errorCode.getCode(), errorCode.getMessage());
  }

  public static Response ok() {
    return new Response(200, "OK");
  }

}
