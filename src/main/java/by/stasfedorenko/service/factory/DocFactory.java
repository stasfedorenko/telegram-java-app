package by.stasfedorenko.service.factory;

import by.stasfedorenko.service.PdfService;
import by.stasfedorenko.service.impl.PdfServiceImpl;

public final class DocFactory {
    private static final DocFactory instance = new DocFactory();
    private final PdfService servicePdf = new PdfServiceImpl();

    private DocFactory() {
    }

    public static DocFactory getInstance() {
        return instance;
    }

    public PdfService getServicePdf() {
        return servicePdf;
    }
}
