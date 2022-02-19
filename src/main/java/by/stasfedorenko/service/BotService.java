package by.stasfedorenko.service;

import by.stasfedorenko.exception.ServiceException;

public interface BotService {
    void runBot(String basePath) throws ServiceException;
}
