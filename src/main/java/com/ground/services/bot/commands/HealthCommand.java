package com.ground.services.bot.commands;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(value = "application.telegram.active", havingValue = "true", matchIfMissing = false)
@Slf4j
public class HealthCommand extends BotCommand {


  private AbsSender absSender;
  private List<Long> chatIds = new ArrayList<>();

  /*
  Flux<ChangeStreamEvent<User>> flux = reactiveTemplate.changeStream(User.class) (1)
    .watchCollection("people")
    .filter(where("age").gte(38))                                              (2)
    .listen();                                                                 (3)
   */


  public HealthCommand() {
    super("health", "health check");
  }


  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

    log.debug("execute args===============================>> {}, {}, {}", user.getFirstName(), chat.getId(), arguments);

    this.absSender = absSender;
    this.chatIds.add(chat.getId());

    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("monitoring started");

    SendMessage answer = new SendMessage();
    answer.setChatId(chat.getId());
    answer.setText(messageBuilder.toString());

    try {
      absSender.execute(answer);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }

  }

  public void Alert(String message) {
    chatIds.forEach(chatId -> {
      SendMessage answer = new SendMessage();
      answer.setChatId(chatId);
      answer.setText(message);
      try {
        if (absSender != null) absSender.execute(answer);
      } catch (TelegramApiException e) {
        log.error("telegram api error", e.getMessage());
      }
    });
  }


}


