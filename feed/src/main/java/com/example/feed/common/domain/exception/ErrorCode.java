package com.example.feed.common.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

  INVALID_INPUT_VALUE(400, "Invalid input value"),
  NOT_FOUND(404, "Not found data"),
  INTERNAL_ERROR(500, "Unexpected error");

  private final int code;
  private final String message;

}
