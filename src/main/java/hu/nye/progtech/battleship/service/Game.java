package hu.nye.progtech.battleship.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import hu.nye.progtech.battleship.persistence.impl.XmlRepositoryImplementation;
import hu.nye.progtech.battleship.service.command.Command;
import hu.nye.progtech.battleship.service.command.CommandHandler;
import hu.nye.progtech.battleship.service.command.impl.DefaultCommand;
import hu.nye.progtech.battleship.service.command.impl.ExitCommand;
import hu.nye.progtech.battleship.service.command.impl.LoadCommand;
import hu.nye.progtech.battleship.ui.LeaderBoardPrinter;
import hu.nye.progtech.battleship.ui.PrintWrapper;

/**
 * Game class, to play torpedo game.
 *
 * @author Szabics
 */
public class Game {
    private Player[] players;

    private final PrintWrapper printWrapper;
    private final int starterPlayer = 0;
    private final int waitingPlayer = 1;

    private boolean shouldExit;

    public Player[] getPlayers() {
        return this.players;
    }

    public Game() {
        this.printWrapper = new PrintWrapper();
    }

    public Game(PrintWrapper printWrapper) {
        this.players = new Player[]{
                new Player(""),
                new Player("")
        };
        this.printWrapper = printWrapper;
        this.shouldExit = false;
    }

    /**
     * The Torpedo game starting method.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        int actualPlayer = this.starterPlayer;
        int playerWaiting = this.waitingPlayer;
        int playerLength = this.players.length;

        Player winner = new Player();
        Player looser = new Player();

        this.players[this.starterPlayer].getNameFromInput();
        this.players[this.starterPlayer].placeShips();

        this.players[this.waitingPlayer].getNameFromInput();
        this.players[this.waitingPlayer].placeShips();

        while (this.players[this.starterPlayer].getTotalLivesLeft() > 0 &&
                this.players[this.waitingPlayer].getTotalLivesLeft() > 0) {

            Fire fire = new Fire(printWrapper);
            fire.fireAt(this.players[actualPlayer++ % playerLength], players[playerWaiting++ % playerLength]);

            for (Player gamer : this.players) {
                gamer.getMap().printBoard();
            }
            if (this.players[this.starterPlayer].getTotalLivesLeft() == 0 || this.players[this.waitingPlayer].getTotalLivesLeft() == 0) {
                winner = this.players[this.starterPlayer].getTotalLivesLeft() == 0
                        ? this.players[this.waitingPlayer]
                        : this.players[this.starterPlayer];
                looser = this.players[this.starterPlayer].getTotalLivesLeft() == 0
                        ? this.players[this.starterPlayer]
                        : this.players[this.waitingPlayer];
            }
        }
        printWrapper.printLine("***** Gratulálok " + winner.getName() + ", Nyertél! *****");

        try {
            PlayerDAO playerDAO = new PlayerDAO();
            Player loadedWinner = playerDAO.selectPlayer(winner.getName());
            Player loadedLooser = playerDAO.selectPlayer(looser.getName());
            boolean shouldInsertWinner = true;
            boolean shouldInsertLooser = true;
            if (loadedWinner != null) {
                winner = loadedWinner;
                shouldInsertWinner = false;
            }
            if (loadedLooser != null) {
                looser = loadedLooser;
                shouldInsertLooser = false;
            }
            winner.setNumberOfGamesWon(winner.getNumberOfGamesWon() + 1);
            winner.setNumberOfPlayedGames(winner.getNumberOfPlayedGames() + 1);
            looser.setNumberOfPlayedGames(looser.getNumberOfPlayedGames() + 1);

            if (shouldInsertWinner) {
                playerDAO.insertPlayer(winner);
            } else {
                playerDAO.updatePlayerDetails(winner);
            }
            if (shouldInsertLooser) {
                playerDAO.insertPlayer(looser);
            } else {
                playerDAO.updatePlayerDetails(looser);
            }
            this.sendToXml();

            List<Command> commandList = List.of(
                    new LoadCommand(playerDAO),
                    new ExitCommand(this),
                    new DefaultCommand(printWrapper)
            );
            CommandHandler inputHandler = new CommandHandler(commandList);
            String userInput = "";
            do {
                printWrapper.printLine("Mi legyen a további teendő?: (load : Ranglista) | (exit : Kilépés)");
                userInput = scanner.nextLine();
                inputHandler.handleCommand(userInput.trim());
            } while (!this.isShouldExit());
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends to XML the actual players.
     */
    public void sendToXml() {
        XmlRepositoryImplementation xmlRepository = new XmlRepositoryImplementation(this.players, this.printWrapper);
        xmlRepository.saveToXML();
    }

    /**
     * Should return true or false.
     *
     * @return booelan property value.
     */
    public boolean isShouldExit() {
        return shouldExit;
    }

    /**
     * The game should exit.
     *
     * @param shouldExit boolean property.
     */
    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

}