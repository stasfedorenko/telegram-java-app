package by.stasfedorenko.command;

public interface Command {
    Router execute(String path);
}
