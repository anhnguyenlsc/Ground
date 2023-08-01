package com.ground.web;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

@Controller
public class IndexPage {
  @CrossOrigin()
  @GetMapping("/")
  public Rendering index(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
    if (oAuth2AuthenticationToken.getPrincipal().getAttributes().containsKey("name")) {
      System.out.println("-------> USERNAME: " + oAuth2AuthenticationToken.getPrincipal().getAttributes().get("name"));
    }
    else if (oAuth2AuthenticationToken.getPrincipal().getAttributes().containsKey("username")) {
      System.out.println("-------> USERNAME: " + oAuth2AuthenticationToken.getPrincipal().getAttributes().get("username"));
    }
    return Rendering.redirectTo("http://localhost:5174").build();
  }

  //  @GetMapping("/signup")
//  public String signup(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("birthday") String birthday) {
//    System.out.println(name + "\n" + email + "\n" + phone + "\n" + birthday);
//    return "index";
//  }

//@CrossOrigin(origins = "http://localhost:8080")

//  @CrossOrigin
//  @GetMapping("http://localhost:5173/signup")
//  public String signup(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("phone") String phone, @RequestParam("birthday") String birthday) {
//    System.out.println(name + "\n" + email + "\n" + phone + "\n" + birthday);
//    return signup(name, email, phone, birthday);
//  }

}
