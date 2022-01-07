package hu.nye.progtech.battleship.service;

import java.awt.Point;
import java.util.InputMismatchException;
import java.util.Scanner;

import hu.nye.progtech.battleship.models.Result;
import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.ui.PrintWrapper;

/**
 * This class for do a fire to a Ship, from another.
 */
public class Fire {

    private final PrintWrapper printWrapper;

    public Fire(PrintWrapper printWrapper) {
        this.printWrapper = printWrapper;
    }

    /**
     * Make a Fire to one ship to another to sink it.
     *
     * @param shooter  who shoot.
     * @param opponent who get the shoot.
     */
    public void fireAt(PlayerInterface shooter, PlayerInterface opponent) {
        printWrapper.print("== " + ((Player) shooter).getName() + " - Add meg a lövés koordinátáit! (1-9 1-9): ");

        boolean isPointValid = false;
        do {
            try {
                Point point = new Point(((Player) shooter).getScanner().nextInt(), ((Player) shooter).getScanner().nextInt());
                int rowCoordinate = (int) point.getX() - 1;
                int columnCoordinate = (int) point.getY() - 1;

                Result result = ((Player) opponent)
                        .getMap()
                        .getField(rowCoordinate, columnCoordinate)
                        .shootAt();

                if (result == Result.PARTIAL_HIT || result == Result.DESTROYED) {
                    ((Player) opponent).setTotalLivesLeft(((Player) opponent).getTotalLivesLeft() - 1);
                }

                isPointValid = true;
            } catch (IllegalArgumentException | InputMismatchException e) {
                this.printWrapper.printLine(e.getMessage());
            }
        } while (!isPointValid);
    }

}
