package by.stasfedorenko.controller;

import by.stasfedorenko.command.Command;
import by.stasfedorenko.command.CommandProvider;
import by.stasfedorenko.command.Impl.CreatePdfCommand;
import by.stasfedorenko.command.PagePath;
import by.stasfedorenko.command.Router;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private final CommandProvider commandProvider = new CommandProvider();
    private static final Logger logger = Logger.getLogger(CreatePdfCommand.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String basePath = request.getServletContext().getRealPath("/");

        String commandName = request.getParameter("command");
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(basePath);

        switch (router.getRouterType()) {
            case REDIRECT:
                response.sendRedirect(router.getPagePath());
                break;
            case FORWARD:
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
                break;
            default:
                logger.error("incorrect route type " + router.getRouterType());
                response.sendRedirect(PagePath.ERROR_PAGE);
        }
    }
}
