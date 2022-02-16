package by.stasfedorenko.parser;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

public class Table {
    private PdfPTable table;
    private static final String[] HEADERS_NAMES = new String[]{"fullName", "reportTitle", "reportBody", "laborCost"};
    private static final int NUMS_COLUMNS = HEADERS_NAMES.length;

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
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    public void createBody(Map<String[], String[]> map) {
        List<String> cols = new ArrayList<>();

        Collection<String[]> keys = map.keySet();

        for (String[] key : keys) {
            String fullName = key[0] + " " + key[1];
            String reportTitle = map.get(key)[0];
            String reportBody = map.get(key)[1];
            String laborCost = map.get(key)[2];

            cols.add(fullName);
            cols.add(reportTitle);
            cols.add(reportBody);
            cols.add(laborCost);

            System.out.println();
        }
        for (String col : cols) {
            table.addCell(col);
        }

        System.out.println();

    }

    public PdfPTable getTable() {
        return this.table;
    }
}
