
import by.stasfedorenko.parser.PdfAppIText;
import by.stasfedorenko.TestBot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TelegramApiException{
        System.out.println("Hello");
        String basePath = "D:\\\\IdeaProjects\\\\telegram-java-app\\\\src\\\\main\\\\resources\\\\docs\\\\";

        TestBot bot = new TestBot(new DefaultBotOptions());
        PdfAppIText pdfAppIText = new PdfAppIText();

        pdfAppIText.execute(basePath);
        bot.execute(basePath);



    }


}
