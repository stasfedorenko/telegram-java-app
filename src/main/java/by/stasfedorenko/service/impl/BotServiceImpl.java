package by.stasfedorenko.service.impl;

import by.stasfedorenko.bots.MyBot;
import by.stasfedorenko.exception.ServiceException;
import by.stasfedorenko.service.BotService;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotServiceImpl implements BotService, Runnable {


    @Override
    public void runBot(String basePath) throws ServiceException {
        MyBot bot = MyBot.getInstance();

        if (bot == null) {
            throw new ServiceException("Bot is not running");
        }
        if (basePath.isEmpty()) {
            throw new ServiceException("The path to Pdf is empty");
        }

        try {
            bot.sendReports(basePath);
        } catch (TelegramApiException e) {
            throw new ServiceException("Bot didn't send message", e);
        }
    }

    public Runnable run(String path) throws ServiceException {
        runBot(path);
        return null;
    }

    @Override
    public void run() {
    }
}
