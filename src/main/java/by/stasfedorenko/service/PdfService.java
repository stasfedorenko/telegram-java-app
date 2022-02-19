package by.stasfedorenko.service;

import by.stasfedorenko.exception.ServiceException;

public interface PdfService {
    void createPdf(String basePath) throws ServiceException;
}
