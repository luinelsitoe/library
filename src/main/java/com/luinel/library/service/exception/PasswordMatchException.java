package com.luinel.library.service.exception;

public class PasswordMatchException extends RuntimeException {
  public PasswordMatchException(String message) {
    super(message);
  }
}
