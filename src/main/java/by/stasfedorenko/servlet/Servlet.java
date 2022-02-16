package by.stasfedorenko.servlet;

import by.stasfedorenko.TestBot;
import by.stasfedorenko.parser.PdfAppIText;
import by.stasfedorenko.parser.PdfAppPdfBox;
import com.itextpdf.text.DocumentException;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            processRequest();
        } catch (TelegramApiException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private void processRequest() throws IOException, TelegramApiException, DocumentException {

        String fileName = "welcome.pdf";
        String path = getServletContext().getInitParameter("uploadFilesPath");
        File file = new File(path + File.separator + fileName);



        System.out.println();


        TestBot bot = new TestBot(new DefaultBotOptions());
        PdfAppIText pdfAppIText = new PdfAppIText();
        pdfAppIText.execute();
//        PdfAppPdfBox pdfAppPdfBox = new PdfAppPdfBox();
//        pdfAppPdfBox.execute();

        bot.execute();
    }
}
