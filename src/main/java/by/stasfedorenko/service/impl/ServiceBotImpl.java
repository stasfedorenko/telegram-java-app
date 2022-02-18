package by.stasfedorenko.service.impl;

import by.stasfedorenko.bots.MyBot;
import by.stasfedorenko.util.PdfAppIText;
import by.stasfedorenko.service.ServiceBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ServiceBotImpl implements ServiceBot {
    @Override
    public void createPDF(String basePath) {
        PdfAppIText pdfAppIText = new PdfAppIText();
        pdfAppIText.createPDF(basePath);

        MyBot bot = new MyBot();
        try {
            bot.sendReports(basePath);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
