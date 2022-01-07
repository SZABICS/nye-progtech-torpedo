package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class SaveCommandTest {

    private SaveCommand underTest;

    private PlayerDAO playerDAO;
    private Player player;

    @BeforeEach
    public void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        underTest = new SaveCommand(playerDAO, player);
    }

    @Test
    public void testSaveCommandCanProcessMethodShouldReturnTrueWhenTheInputIsSave() {
        // Given
        String input = "save";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSaveCommandCanProcessMethodShouldReturnFalseWhenTheInputIsNotSave() {
        // Given
        String input = "load";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSaveCommandCanProcessMethodShouldReturnFalseWhenTheInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSaveCommandSaveProcessCanSaveToRepository() {
        // Given
        String input = "save";

        // When
        underTest.process(input);

        // Then
        Mockito.verify(playerDAO).insertPlayer(player);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

    @Test
    public void testSaveCommandSaveProcessCanNotSaveToRepositoryIfTheInputValueIsNull() {
        // Given
        String input = null;

        // When
        underTest.process(input);

        // Then
        Mockito.verify(playerDAO).insertPlayer(player);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

}