package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.service.Game;
import hu.nye.progtech.battleship.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to exit from the game.
 */
public class ExitCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExitCommand.class);

    private static final String EXIT_COMMAND = "exit";

    private final Game game;

    public ExitCommand(Game game) {
        this.game = game;
    }

    @Override
    public boolean canProcess(String input) {
        return EXIT_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.info("Kilépés a játékból.");
        game.setShouldExit(true);
    }
}