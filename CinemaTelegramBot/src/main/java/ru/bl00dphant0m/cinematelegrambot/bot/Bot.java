package ru.bl00dphant0m.cinematelegrambot.bot;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bl00dphant0m.cinematelegrambot.model.MovieInfo;
import ru.bl00dphant0m.cinematelegrambot.model.User;
import ru.bl00dphant0m.cinematelegrambot.service.UserService;

@Component
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    @Value("${telegram.token}")
    private String botToken;

    @Value("${telegram.bot-name}")
    private String botName;

    private final UserService userService;

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long id = update.getMessage().getChatId();
        User userWithID = userService.getUserById(id);

        if (userWithID == null) {
            User user = new User();
            user.setChatID(update.getMessage().getChatId());
            user.setLogin(update.getMessage().getFrom().getUserName());
            userService.saveUser(user);
            sendMessage(id, "Вы подписаны на рассылку");
        }
    }

    public void  sendMessage(Long chatId,String text){
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void  sendMessageAboutMovie(Long chatId, MovieInfo movieInfo){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(String.valueOf(chatId));
        sendPhoto.setPhoto(new InputFile(movieInfo.getUrl()));
        sendPhoto.setCaption(movieInfo.getInfo());

        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
