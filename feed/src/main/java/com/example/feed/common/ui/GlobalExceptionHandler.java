package com.example.feed.common.ui;

import com.example.feed.common.domain.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(IllegalArgumentException.class)
  public Response handleIllegalArgumentException(IllegalArgumentException exception) {
    return Response.error(ErrorCode.INVALID_INPUT_VALUE);
  }

//  @ExceptionHandler(Exception.class)
  public Response handleException(Exception exception) {
    log.error(exception.getMessage());
    return Response.error(ErrorCode.INVALID_INPUT_VALUE);
  }

}
