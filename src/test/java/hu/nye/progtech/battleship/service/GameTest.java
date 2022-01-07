package hu.nye.progtech.battleship.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Unit tests for Game.
 */
@ExtendWith(MockitoExtension.class)
public class GameTest {

    @Mock
    private Game underTest;

    private PrintWrapper printWrapper;


    @Test
    public void testTheGameShouldExit() {
        // given
        underTest = new Game();

        // when
        underTest.setShouldExit(true);

        // then
        assertTrue(underTest.isShouldExit());
    }

    @Test
    public void testTheGameShouldNotExit() {
        // given
        underTest = new Game();

        // when
        underTest.setShouldExit(false);

        // then
        assertFalse(underTest.isShouldExit());
    }
    @Test
    public void testGameCanHandlePlayers() {
        // given
        underTest = new Game();

        // when
        underTest.setShouldExit(false);

        // then
        assertFalse(underTest.isShouldExit());
    }

    @Test
    public void testTheFireIsInicialized() {
        // given
        underTest = new Game(new PrintWrapper());
        Player[] players = underTest.getPlayers();

        // then
        Assertions.assertNotNull(players);
    }
    @Test
    public void testSendToXmlMethodIfProcessRight() {
        // given
        underTest = new Game(new PrintWrapper());
        Player[] players = underTest.getPlayers();

        // then
        Assertions.assertNotNull(players);
        underTest.sendToXml();
    }

    /*
    @Test
    public void testGameStartMethod() {
        // given
        underTest = new Game(new PrintWrapper());

        verifyNoInteractions(underTest);
    }
    */

}