package hu.nye.progtech.battleship.models.player.impl;

import java.awt.Point;
import java.util.InputMismatchException;
import java.util.Scanner;

import hu.nye.progtech.battleship.models.Map;
import hu.nye.progtech.battleship.models.Result;
import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.ui.PrintWrapper;

/**
 * Player class to implement a Player in the game, and let it play.
 *
 * @author Szabics
 */
public class Player implements PlayerInterface {
    private String name;

    private int numberOfGamesWon;
    private int numberOfPlayedGames;
    private int totalLivesLeft = 3;

    private Map map;
    private Scanner scanner;
    private final PrintWrapper printWrapper;

    public Player(String name) {
        this.name = name;
        this.map = new Map();
        this.scanner = new Scanner(System.in);
        this.printWrapper = new PrintWrapper();
        this.numberOfGamesWon = 0;
        this.numberOfPlayedGames = 0;
    }

    public Player(String name, int numberOfGamesWon, int numberOfPlayedGames) {
        this.name = name;
        this.printWrapper = new PrintWrapper();
        this.numberOfGamesWon = numberOfGamesWon;
        this.numberOfPlayedGames = numberOfPlayedGames;
    }

    public Player() {
        this.printWrapper = new PrintWrapper();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfGamesWon() {
        return numberOfGamesWon;
    }

    public void setNumberOfGamesWon(int numberOfGamesWon) {
        this.numberOfGamesWon = numberOfGamesWon;
    }

    public int getNumberOfPlayedGames() {
        return numberOfPlayedGames;
    }

    public void setNumberOfPlayedGames(int numberOfPlayedGames) {
        this.numberOfPlayedGames = numberOfPlayedGames;
    }

    public void setTotalLivesLeft(int totalLivesLeft) {
        this.totalLivesLeft = totalLivesLeft;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void placeShips() {
        printWrapper.printLine("======== " + name + " - Hajók elhelyezése ========");
        this.map.placeShipsOnBoard();
    }

    @Override
    public void getNameFromInput() {
        printWrapper.printLine("======== Kérem adja meg a Játékos nevét!: ");
        this.setName(scanner.nextLine());
    }

    @Override
    public int getTotalLivesLeft() {
        return totalLivesLeft;
    }

    @Override
    public String toString() {
        return this.getName() + " --- " + this.getNumberOfGamesWon() + " --- " + this.getNumberOfPlayedGames();
    }
}