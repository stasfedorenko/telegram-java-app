package by.stasfedorenko.command.Impl;

import by.stasfedorenko.command.Command;
import by.stasfedorenko.command.PagePath;
import by.stasfedorenko.command.Router;
import by.stasfedorenko.exception.ServiceException;
import by.stasfedorenko.service.BotService;
import by.stasfedorenko.service.PdfService;
import by.stasfedorenko.service.factory.DocFactory;
import by.stasfedorenko.service.impl.BotServiceImpl;
import org.apache.log4j.Logger;

public class RunCommand implements Command {
    private static final Logger logger = Logger.getLogger(CreatePdfCommand.class);
    @Override
    public Router execute(String path) {
        Router router;
        DocFactory docFactory = DocFactory.getInstance();
        PdfService pdfService = docFactory.getServicePdf();
        BotService serviceBot = new BotServiceImpl();
        try {
            pdfService.createPdf(path);
            serviceBot.runBot(path);
            router = new Router(PagePath.DONE_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at run program", e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
