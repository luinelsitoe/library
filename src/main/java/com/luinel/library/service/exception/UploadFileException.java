package com.luinel.library.service.exception;

import java.io.IOException;

public class UploadFileException extends RuntimeException {
  public UploadFileException(String message, IOException ex) {
    super(message, ex);
  }
}
