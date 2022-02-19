package by.stasfedorenko.command.Impl;

import by.stasfedorenko.command.Command;
import by.stasfedorenko.command.PagePath;
import by.stasfedorenko.command.Router;
import by.stasfedorenko.exception.ServiceException;
import by.stasfedorenko.service.PdfService;
import by.stasfedorenko.service.factory.DocFactory;
import org.apache.log4j.Logger;


public class CreatePdfCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreatePdfCommand.class);
    @Override
    public Router execute(String path) {
        Router router;
        DocFactory docFactory = DocFactory.getInstance();
        PdfService pdfService = docFactory.getServicePdf();

        try {
            pdfService.createPdf(path);
            router = new Router(PagePath.GO_TO_START_BOT, Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at CreatePdfCommand", e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
