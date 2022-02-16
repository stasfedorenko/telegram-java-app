package by.stasfedorenko.parser;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfAppIText {
    public void execute() {
        Document document = new Document();
        Table table = new Table();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String dateResult = formatter.format(date);

        try {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\IdeaProjects\\telegram-java-app\\src\\main\\resources\\docs\\welcome.pdf"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/opt/tomcat/conf/docs/welcome.pdf"));

            document.open();
            document.add(new Paragraph("Hi, we are Yellow command.\nNice to meet you!\n"+dateResult));
            table.createTable();
            document.add(new Paragraph("\n"));
            document.add(table.getTable());

            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}


