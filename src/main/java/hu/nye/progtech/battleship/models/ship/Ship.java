package hu.nye.progtech.battleship.models.ship;

import hu.nye.progtech.battleship.models.Result;
import hu.nye.progtech.battleship.ui.PrintWrapper;

/**
 * Ship class to play game with ships.
 *
 * @author Szabics
 */
public class Ship {
    private final String name;
    private final int size;
    private int lives;
    private boolean destroyed;
    private static final int SHIP_SIZE = 1;
    private final PrintWrapper printWrapper;

    public Ship(String name) {
        this.name = name;
        this.size = SHIP_SIZE;
        this.lives = size;
        this.destroyed = false;
        this.printWrapper = new PrintWrapper();
    }

    public Ship(String name, int lives, boolean destroyed) {
        this.name = name;
        this.size = SHIP_SIZE;
        this.lives = lives;
        this.destroyed = destroyed;
        this.printWrapper = new PrintWrapper();
    }

    /**
     * Takes a hit on the ship.
     */
    public void hit() {
        if (lives > 0) {
            printWrapper.printLine("Szép lövés! A " + name + " eltalálva!");
            lives--;
        } else {
            printWrapper.printLine("A " + name + " már elsüllyedt!");
        }
    }

    /**
     * Gives back the actual state of ship on field.
     *
     * @return Result
     */
    public Result getState() {
        if (lives == 0) {
            return Result.DESTROYED;
        } else if (lives < size) {
            return Result.PARTIAL_HIT;
        } else {
            return Result.NO_HIT;
        }
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getLives() {
        return lives;
    }
}