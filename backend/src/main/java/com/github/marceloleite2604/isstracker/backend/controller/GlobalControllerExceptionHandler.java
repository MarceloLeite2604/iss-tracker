package com.github.marceloleite2604.isstracker.backend.controller;

import com.github.marceloleite2604.isstracker.backend.exception.IssInformationUnavailableException;
import com.github.marceloleite2604.isstracker.backend.exception.IssRouteMapUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

  @ExceptionHandler({IssInformationUnavailableException.class, IssRouteMapUnavailableException.class})
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ResponseEntity<String> issInformationUnavailable(RuntimeException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
  }
}
