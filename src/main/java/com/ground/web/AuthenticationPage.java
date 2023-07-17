package com.ground.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
@RequestMapping("/authentication")
public class AuthenticationPage {
  @GetMapping("/in")
  public Rendering in() {
    return Rendering.view("authentication/in").build();
  }
  @GetMapping("/up")
  public Rendering up() {
    return Rendering.view("authentication/up").build();
  }
}
