package hu.nye.progtech.battleship.service.command;

/**
 * Command what can be used while playing the game.
 */
public interface Command {

    /**
     * Check the command can be processed.
     *
     * @param input int input
     * @return {@code true} Can process, {@code false} Can not process.
     */
    boolean canProcess(String input);

    /**
     * Processes the user's input.
     *
     * @param input the input as a string to be processed
     */
    void process(String input);

}