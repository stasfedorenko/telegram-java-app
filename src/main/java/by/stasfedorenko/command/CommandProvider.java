package by.stasfedorenko.command;

import by.stasfedorenko.command.Impl.CreatePdfCommand;
import by.stasfedorenko.command.Impl.DefaultCommand;
import by.stasfedorenko.command.Impl.RunCommand;
import by.stasfedorenko.command.Impl.StartBotCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {
        repository.put(CommandName.CREATE_PDF, new CreatePdfCommand());
        repository.put(CommandName.DEFAULT, new DefaultCommand());
        repository.put(CommandName.START_BOT, new StartBotCommand());
        repository.put(CommandName.RUN, new RunCommand());
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.DEFAULT);
        }
        return command;
    }
}

