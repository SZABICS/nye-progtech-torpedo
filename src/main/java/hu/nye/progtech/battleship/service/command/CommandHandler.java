package hu.nye.progtech.battleship.service.command;

import java.util.List;

/**
 * Component that handles a given input.
 */
public class CommandHandler {

    private final List<Command> commandList;

    public CommandHandler(List<Command> commandList) {
        this.commandList = commandList;
    }

    /**
     * Try to handle user command.
     *
     * @param input user input reading.
     */
    public void handleCommand(String input) {
        for (Command command : commandList) {
            if (command.canProcess(input)) {
                command.process(input);
                break;
            }
        }
    }

}
