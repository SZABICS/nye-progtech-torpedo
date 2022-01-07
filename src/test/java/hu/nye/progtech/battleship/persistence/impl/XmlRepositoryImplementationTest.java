package hu.nye.progtech.battleship.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.service.Game;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class XmlRepositoryImplementationTest {

    private XmlRepositoryImplementation underTest;

    private Game game;

    private Player[] players;

    private PrintWrapper printWrapper;

    @BeforeEach
    public void init() {
        game = new Game(printWrapper);
        players = game.getPlayers();

        players[0].setTotalLivesLeft(1);
        players[0].setName("Géza");
        players[1].setName("Benedek");

        underTest = new XmlRepositoryImplementation(players, printWrapper);
    }

    @Test
    public void testSaveXmlInitValid() {
        Assertions.assertNotNull(underTest);
    }

    @Test
    public void testSaveToXmlProcessIsSuccessWithGiveClasses() {
        underTest.saveToXML();
    }

}