package hu.nye.progtech.battleship.service.command.impl;

import java.util.List;

import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import hu.nye.progtech.battleship.service.command.Command;
import hu.nye.progtech.battleship.ui.LeaderBoardPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Command used to load a previously saved game state.
 */
public class LoadCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadCommand.class);
    private static final String LOAD_COMMAND = "load";

    private PlayerDAO playerDAO;
    private List<Player> players;

    public LoadCommand(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public boolean canProcess(String input) {
        return LOAD_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Betöltés meghívása.");
        players = playerDAO.selectAllPlayer();
        LeaderBoardPrinter printer = new LeaderBoardPrinter(players);
        printer.printOutLeaderBoard();

        LOGGER.info("Betöltés sikeres!");
    }

}