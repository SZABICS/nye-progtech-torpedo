package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import hu.nye.progtech.battleship.ui.LeaderBoardPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class LoadCommandTest {

    private LoadCommand underTest;

    private PlayerDAO playerDAO;

    private LeaderBoardPrinter printer;

    @BeforeEach
    public void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        printer = Mockito.mock(LeaderBoardPrinter.class);
        underTest = new LoadCommand(playerDAO);
    }

    @Test
    public void testLoadCommandCanProcessShouldReturnTrueWhenInputIsLoad() {
        // Given
        String input = "load";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNotLoad() {
        // Given
        String input = "save";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

    @Test
    public void testCanProcessShouldReturnFalseWhenInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

    @Test
    public void testLoadCommandLoadMethodCanLoadPlayers() {
        // Given
        String input = "load";

        // When
        underTest.process(input);

        // Then
        Mockito.verify(playerDAO).selectAllPlayer();
        Mockito.verifyNoMoreInteractions(printer);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

}