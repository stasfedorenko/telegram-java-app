package by.stasfedorenko.parser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfAppIText {
    public void execute() throws IOException {
        Document document = new Document();
        Table table = new Table();

        try {
            BaseFont font = BaseFont.createFont("D:\\IdeaProjects\\telegram-java-app\\src\\main\\resources\\fonts\\times.ttf", "cp1251", BaseFont.EMBEDDED);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\IdeaProjects\\telegram-java-app\\src\\main\\resources\\docs\\welcome.pdf"));

            document.open();
            document.add(new Paragraph("Привет Всем.\n", new Font(font, 30)));
//            document.add(new Paragraph("Hi, WORLD.\n"));
            table.createTable();
            document.add(new Paragraph("\n"));
            document.add(table.getTable());

            document.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


