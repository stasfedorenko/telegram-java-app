package by.stasfedorenko.bots;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class MyBot extends TelegramLongPollingBot {
    private static final String CHAT_ID = "798726464";
//    private static final String CHAT_ID = "313204287"; // Yra telega chat_id


    @Override
    public String getBotUsername() {
        return "@yellow_command_reports_bot";
    }

    @Override
    public String getBotToken() {
        return "5126244951:AAGGgW4_aHBDJBHsjegm8ppMl8pZOFFC9Yg";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message.hasText()) {
                try {
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).
                            text("Hello, " + message.getFrom().getFirstName()).build());
                    execute(SendMessage.builder().chatId(message.getChatId().toString()).
                            text("Have a nice day, " + message.getFrom().getFirstName()).build());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void execute(String basePath) throws TelegramApiException {
        String PATH = basePath + "welcome.pdf";
        this.execute(SendMessage.builder().chatId(CHAT_ID).text("Hi, this is our reports list").build());
        this.execute(SendDocument.builder().chatId(CHAT_ID).document(new InputFile(new File(PATH))).build());
    }
}
