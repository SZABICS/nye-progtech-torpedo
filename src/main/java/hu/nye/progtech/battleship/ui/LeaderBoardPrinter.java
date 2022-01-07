package hu.nye.progtech.battleship.ui;

import java.util.List;

import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;

/**
 * It's a Util class to wrap print operations.
 */
public class LeaderBoardPrinter {

    private List<Player> players;
    private PrintWrapper printWrapper;

    public LeaderBoardPrinter(List<Player> players) {
        this.players = players;
        this.printWrapper = new PrintWrapper();
    }

    /**
     * Prints out the leaderboard of last players on descending order.
     */
    public void printOutLeaderBoard() {
        printWrapper.printLine("********** RANGLISTA **********");
        printWrapper.printLine("Név - Győzelem - Játszott meccs");
        for (Player player : this.players) {
            printWrapper.printLine(player.toString());
        }
        printWrapper.printLine("**********************");
    }

}