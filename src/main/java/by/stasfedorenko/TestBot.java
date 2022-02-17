package by.stasfedorenko;

import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class TestBot extends DefaultAbsSender {

    private final String BOT_TOKEN = "5126244951:AAGGgW4_aHBDJBHsjegm8ppMl8pZOFFC9Yg";
    //    private final String CHAT_ID = "798726464";
//    private final String CHAT_ID = "496687309";
    private final String CHAT_ID = "906609026";


    public TestBot(DefaultBotOptions options) {
        super(options);
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void execute(String basePath) throws TelegramApiException {
        this.execute(SendMessage.builder().chatId(this.CHAT_ID).text("Hello everybody!").build());
        //    private final String PATH = "D:\\IdeaProjects\\telegram-java-app\\src\\main\\resources\\docs\\welcome.pdf";
        String PATH = basePath + "welcome.pdf";
        this.execute(SendDocument.builder().chatId(this.CHAT_ID).document(new InputFile(new File(PATH))).build());
    }


}
