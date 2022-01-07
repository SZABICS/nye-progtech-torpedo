package hu.nye.progtech.battleship.service.command.impl;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.impl.PlayerDAO;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class UpdateCommandTest {

    private UpdateCommand underTest;

    private PlayerDAO playerDAO;
    private Player player;

    @BeforeEach
    public void init() {
        playerDAO = Mockito.mock(PlayerDAO.class);
        underTest = new UpdateCommand(playerDAO, player);
    }

    @Test
    public void testUpdateCommandCanProcessMethodShouldReturnTrueWhenTheInputIsUpdate() {
        // Given
        String input = "update";
        boolean expected = true;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCommandCanProcessMethodShouldReturnFalseWhenTheInputIsNotUpdate() {
        // Given
        String input = "load";
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCommandCanProcessMethodShouldReturnFalseWhenTheInputIsNull() {
        // Given
        String input = null;
        boolean expected = false;

        // When
        boolean actual = underTest.canProcess(input);

        // Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testUpdateCommandUpdateProcessCanUpdateToRepository() {
        // Given
        String input = "update";

        // When
        underTest.process(input);

        // Then
        Mockito.verify(playerDAO).updatePlayerDetails(player);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

    @Test
    public void testUpdateCommandUpdateProcessCanNotUpdateToRepositoryIfTheInputValueIsNull() {
        // Given
        String input = null;

        // When
        underTest.process(input);

        // Then
        Mockito.verify(playerDAO).updatePlayerDetails(player);
        Mockito.verifyNoMoreInteractions(playerDAO);
    }

}