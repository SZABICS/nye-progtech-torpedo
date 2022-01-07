package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.service.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class unit testing for ExitCommand Class.
 */
public class ExitCommandTest {

    private static final String EXIT_COMMAND = "exit";
    private static final String NOT_EXIT_COMMAND = "no-exit";

    private Game game;

    private ExitCommand underTest;

    @BeforeEach
    public void setUp() {
        game = new Game();
        underTest = new ExitCommand(game);
    }

    @Test
    public void testCanProcessThatTheProcessShouldReturnTrueIfInputIsExit() {
        // when
        boolean result = underTest.canProcess(EXIT_COMMAND);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessThatTheProcessShouldReturnFalseIfInputIsNotExit() {
        // when
        boolean result = underTest.canProcess(NOT_EXIT_COMMAND);

        // then
        assertFalse(result);
    }

    @Test
    public void testMethodShouldChangeShouldExitOfGame() {
        // when
        underTest.process(EXIT_COMMAND);

        // then
        assertTrue(game.isShouldExit());
    }

}
