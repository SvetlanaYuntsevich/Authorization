package by.epam.jwd.controller;

import by.epam.jwd.controller.command.Command;
import by.epam.jwd.controller.command.CommandName;
import by.epam.jwd.controller.command.impl.user.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTER, new Register());
        repository.put(CommandName.TO_REGISTER, new ToRegisterPage());
        repository.put(CommandName.TO_LOGIN, new ToLoginPage());
        repository.put(CommandName.WELCOME, new Welcome());
        repository.put(CommandName.SIGN_OUT, new SignOut());

  
    }

    Command getCommand(String name){
        CommandName commandName;
        Command command;
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
      

        return command;
    }
}
