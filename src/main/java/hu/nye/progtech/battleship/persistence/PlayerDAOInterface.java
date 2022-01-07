package hu.nye.progtech.battleship.persistence;

import java.util.List;

import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;

/**
 * Interface for PlayerDAO.
 */
public interface PlayerDAOInterface {

    void insertPlayer(Player player);

    Player selectPlayer(String name);

    List<Player> selectAllPlayer();

    void updatePlayerDetails(Player player);
}