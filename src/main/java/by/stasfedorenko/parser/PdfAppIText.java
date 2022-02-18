package by.stasfedorenko.parser;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfAppIText {
    private static final Font DOCUMENT_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20);
    public void execute(String basePath) {
        Document document = new Document();
        Table table = new Table();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(basePath + "reports.pdf"));
            Image logo = Image.getInstance(basePath +"logo.png");
            document.open();
            document.add(new Paragraph("Hi, we are Yellow command.\nThis are reports about our workday!\n\n",DOCUMENT_TITLE_FONT));
            table.createTable();
            document.add(table.getTable());
            document.add(logo);
            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}


