package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.service.command.Command;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default command, when the given command is bad.
 */
public class DefaultCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCommand.class);

    private static final String UNKNOWN_COMMAND_MESSAGE = "Ismeretlen";

    private final PrintWrapper printWrapper;

    public DefaultCommand(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    @Override
    public boolean canProcess(String input) {
        return true;
    }

    @Override
    public void process(String input) {
        LOGGER.info("Ismeretlen.");
        printWrapper.printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}