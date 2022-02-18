package by.stasfedorenko.parser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Table {
    private PdfPTable table;
    private static final String[] HEADERS_NAMES = new String[]{"NAME", "REPORT TITLE", "REPORT", "LABOR COST"};
    private static final int NUMS_COLUMNS = HEADERS_NAMES.length;
    private static final Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static final Font COLS_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 14);

    public void createTable() throws IOException {
        Map<String[], String[]> map = JSONtoMap.execute(JSON.getJSON());
        table = new PdfPTable(NUMS_COLUMNS);
        createHeader();
        createBody(map);
    }

    public void createHeader() {
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

    public void createBody(Map<String[], String[]> map) {
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
                cell.setVerticalAlignment(1);
                cell.setHorizontalAlignment(1);
                cell.setPadding(15);
                cell.setPhrase(new Phrase(cellsNames.get(i), COLS_FONT));
                table.addCell(cell);
            }
        }
    }

    public PdfPTable getTable() {
        return this.table;
    }
}
