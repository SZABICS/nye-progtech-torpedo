package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.PlayerDAOInterface;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import hu.nye.progtech.battleship.service.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is used to save a player.
 */
public class SaveCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveCommand.class);
    private static final String SAVE_COMMAND = "save";

    private PlayerDAOInterface playerDao;
    private Player player;

    public SaveCommand(PlayerDAO playerDao, Player player) {
        this.playerDao = playerDao;
        this.player = player;
    }

    @Override
    public boolean canProcess(String input) {
        return SAVE_COMMAND.equals(input);
    }

    @Override
    public void process(String input) {
        LOGGER.debug("Mentés meghívásra került.");
        playerDao.insertPlayer(player);
        LOGGER.info("Mentés sikeres!");
    }

}
