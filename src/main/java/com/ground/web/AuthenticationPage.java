package com.ground.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ground.support.SecurityUtils;
import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;

import java.text.ParseException;

@Controller
@RequestMapping("/authentication")
@Slf4j
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
