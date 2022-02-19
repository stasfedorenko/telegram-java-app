package by.stasfedorenko.command.Impl;

import by.stasfedorenko.command.Command;
import by.stasfedorenko.command.PagePath;
import by.stasfedorenko.command.Router;
import by.stasfedorenko.exception.ServiceException;
import by.stasfedorenko.service.BotService;
import by.stasfedorenko.service.impl.BotServiceImpl;
import org.apache.log4j.Logger;

public class StartBotCommand implements Command {
    private static final Logger logger = Logger.getLogger(StartBotCommand.class);
    @Override
    public Router execute(String path) {
        Router router;
        BotService serviceBot = new BotServiceImpl();
        try {
            serviceBot.runBot(path);
            router = new Router(PagePath.INDEX, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Some problems with Bot ", e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
