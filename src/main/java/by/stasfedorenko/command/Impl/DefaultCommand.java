package by.stasfedorenko.command.Impl;

import by.stasfedorenko.command.Command;
import by.stasfedorenko.command.PagePath;
import by.stasfedorenko.command.Router;

public class DefaultCommand implements Command {

    @Override
    public Router execute(String path) {
        return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
    }
}
