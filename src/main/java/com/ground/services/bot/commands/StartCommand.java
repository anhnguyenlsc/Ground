package com.ground.services.bot.commands;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class StartCommand extends BotCommand {


  public StartCommand() {
    super("start", "Start command");
  }


  @Override
  public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {

    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("Hello, ").append(user.getFirstName()).append(" ").append(user.getLastName());


    SendMessage answer = new SendMessage();
    answer.setChatId(chat.getId().toString());
    answer.setText(messageBuilder.toString());

    try {
      absSender.execute(answer);
    } catch (TelegramApiException e) {
      e.printStackTrace();
    }

  }


}
