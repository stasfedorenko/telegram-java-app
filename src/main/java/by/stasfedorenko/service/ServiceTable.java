package by.stasfedorenko.service;

import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.Map;

public interface ServiceTable {
    void createTable() throws IOException;
    void createHeader();
    void createBody(Map<String[], String[]> map);
    PdfPTable getTable();
}
