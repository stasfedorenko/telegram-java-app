package by.stasfedorenko.servlet;

import by.stasfedorenko.service.ServiceBot;
import by.stasfedorenko.service.impl.ServiceBotImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Servlet extends HttpServlet {
    private final ServiceBot serviceBot = new ServiceBotImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String basePath = req.getServletContext().getRealPath("/");
        serviceBot.createPDF(basePath);
    }
}
