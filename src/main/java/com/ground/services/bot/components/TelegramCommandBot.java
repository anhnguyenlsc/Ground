package com.ground.services.bot.components;

import com.ground.services.bot.commands.HealthCommand;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Update;

//import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;


@Slf4j
@Getter
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "application.telegram.active", havingValue = "true", matchIfMissing = false)
public class TelegramCommandBot extends TelegramLongPollingCommandBot {


  @Value("${application.telegram.bot.token}")
  private String botToken;

  @Value("${application.telegram.bot.username}")
  private String botUsername;

  private Long chat_id = null;

  private final HealthCommand healthCommand;



  @PostConstruct
  public void init() {
    register(healthCommand);
  }

  @Override
  public void processNonCommandUpdate(Update update) {



  }



}
