package by.stasfedorenko.parser;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfAppIText {
    public void execute(String basePath) {
        Document document = new Document();
        Table table = new Table();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(basePath + "reports.pdf"));
            document.open();
            document.add(new Paragraph("Hi, we are Yellow command.\nThis reports about our workday!\n"));
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


