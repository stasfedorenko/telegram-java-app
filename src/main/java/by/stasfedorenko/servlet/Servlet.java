package by.stasfedorenko.servlet;

import by.stasfedorenko.bots.MyBot;
import by.stasfedorenko.parser.PdfAppIText;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String basePath = req.getServletContext().getRealPath("/");

        MyBot bot = new MyBot();
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        PdfAppIText pdfAppIText = new PdfAppIText();
        pdfAppIText.execute(basePath);
        try {
            bot.execute(basePath);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
