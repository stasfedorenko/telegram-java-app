package by.stasfedorenko.service.impl;

import by.stasfedorenko.exception.ServiceException;
import by.stasfedorenko.service.PdfService;
import by.stasfedorenko.util.ParserJSON;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class PdfServiceImpl implements PdfService {

    private static final Font DOCUMENT_TITLE_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 20);
    private static final String[] HEADERS_NAMES = new String[]{"NAME", "REPORT TITLE", "REPORT", "LABOR COST"};
    private static final int NUMS_COLUMNS = HEADERS_NAMES.length;
    private static final Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font COLS_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14);

    @Override
    public void createPdf(String basePath) throws ServiceException {
        if (basePath.isEmpty()) {
            throw new ServiceException("The path to Pdf is empty");
        }
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(basePath + "reports.pdf"));
            document.open();
            document.add(new Paragraph("Hi, we are Yellow command.\nThis are reports about our workday!\n\n", DOCUMENT_TITLE_FONT));
            document.add(createTable());
            document.close();
            writer.close();
        } catch (DocumentException | IOException e) {
            throw new ServiceException("Some problems with create Pdf",e);
        }
    }

    public PdfPTable createTable() throws IOException {
        Map<String[], String[]> map = ParserJSON.execute();
        PdfPTable table = new PdfPTable(NUMS_COLUMNS);
        createHeader(table);
        createBody(map, table);
        table.setWidthPercentage(100);
        return table;
    }

    public void createHeader(PdfPTable table) {
        Stream.of(HEADERS_NAMES)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(2);
                    header.setVerticalAlignment(1);
                    header.setHorizontalAlignment(1);
                    header.setPadding(15);
                    header.setPhrase(new Phrase(columnTitle, HEADER_FONT));
                    table.addCell(header);
                });
    }

    public void createBody(Map<String[], String[]> map, PdfPTable table) {
        Collection<String[]> keys = map.keySet();

        for (String[] key : keys) {
            List<String> cellsNames = new ArrayList<>();
            cellsNames.add(key[0] + " " + key[1]);
            cellsNames.add(map.get(key)[0]);
            cellsNames.add(map.get(key)[1]);
            cellsNames.add(map.get(key)[2]);

            for (int i = 0; i < NUMS_COLUMNS; i++) {
                PdfPCell cell = new PdfPCell();
                cell.setBorderWidth(2);
                cell.setPadding(10);
                cell.setPhrase(new Phrase(cellsNames.get(i), COLS_FONT));
                table.addCell(cell);
            }
        }
    }
}
