package com.ground.controller;


import com.ground.services.bot.components.TelegramCommandBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private ReactiveMongoTemplate template;

}
