package com.ground.support;

public class InvalidPasswordException extends RuntimeException{

  public InvalidPasswordException() {
    super("Incorrect password");
  }
}
