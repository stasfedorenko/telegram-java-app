package by.stasfedorenko.servlet;

import by.stasfedorenko.TestBot;
import by.stasfedorenko.parser.PdfAppIText;
import com.itextpdf.text.DocumentException;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            processRequest();
        } catch (TelegramApiException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws TelegramApiException, DocumentException {
        TestBot bot = new TestBot(new DefaultBotOptions());
        PdfAppIText pdfAppIText = new PdfAppIText();
        pdfAppIText.execute();
        bot.execute();
    }
}
