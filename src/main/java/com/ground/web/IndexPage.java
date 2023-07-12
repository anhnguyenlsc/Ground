package com.ground.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class IndexPage {
  @GetMapping("/")
  public Rendering index() {
    return Rendering.view("index").build();
  }


}
