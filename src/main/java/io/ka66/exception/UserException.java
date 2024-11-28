package io.ka66.exception;

public class UserException {

  public static class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
      super(message);
    }
  }
}
