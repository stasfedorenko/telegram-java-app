package by.stasfedorenko.util;

import java.io.FileOutputStream;
import java.io.IOException;

import by.stasfedorenko.service.PdfService;
import by.stasfedorenko.service.impl.PdfServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class PdfAppIText {
    private static final Font DOCUMENT_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20);
    PdfService serviceTable = new PdfServiceImpl();

//    public void createPDF(String basePath) {
//        Document document = new Document();
//        try {
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(basePath + "reports.pdf"));
//            document.open();
//            document.add(new Paragraph("Hi, we are Yellow command.\nThis are reports about our workday!\n\n", DOCUMENT_TITLE_FONT));
//            serviceTable.createTable();
//            document.add(serviceTable.getTable());
//            document.close();
//            writer.close();
//        } catch (DocumentException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}


