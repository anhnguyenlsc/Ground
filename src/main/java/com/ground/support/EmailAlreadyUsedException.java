package com.ground.support;

public class EmailAlreadyUsedException extends RuntimeException{

  public EmailAlreadyUsedException() {
    super("Email is already in use!");
  }
}
