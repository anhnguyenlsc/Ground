package com.ground.services.bot.components;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;


@Slf4j
@Getter
//@Component
public class TelegramBot extends TelegramLongPollingBot {


  @Value("${application.telegram.bot.token}")
  private String botToken;

  @Value("${application.telegram.bot.username}")
  private String botUsername;

  private Long chat_id = null;



  @Override
  public void onUpdateReceived(Update update) {
    log.info("Update received: {}", update);

    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
      // Set variables
      String message_text = update.getMessage().getText();
      chat_id = update.getMessage().getChatId();




      SendMessage message = new SendMessage();
      message.setChatId(chat_id);
      message.setText(message_text);


      try {
        execute(message); // Sending our message object to user
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }

  }

  @Scheduled(fixedDelay = 1000)
  public void send() {
    if(chat_id == null) return;
    SendMessage message = new SendMessage();
    message.setChatId(chat_id);
    message.setText("Hello");
    try {
      execute(message); // Sending our message object to user
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }
  }


}
