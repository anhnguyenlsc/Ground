package com.ground.support;

public class UsernameAlreadyUsedException extends RuntimeException  {

public UsernameAlreadyUsedException() {
    super("Login name already used!");
  }

}
