package hu.nye.progtech.battleship.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import hu.nye.progtech.battleship.models.player.impl.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayerDAOTest {

    private PlayerDAO underTest;

    private Connection connection;

    private Player player;

    @BeforeEach
    public void init() throws SQLException {
        connection = Mockito.mock(Connection.class);

        underTest = new PlayerDAO();

        player = new Player("Teszt", 1, 2);
    }

    @Test
    public void testSaveShouldStoreTheNewOneWhenThereIsNoException() throws SQLException {
        // Given
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        Mockito.when(connection.prepareStatement(PlayerDAO.CREATE_STATEMENT))
                .thenReturn(preparedStatement);

        // When
        underTest.insertPlayer(player);

        // Then
        Mockito.verifyNoMoreInteractions(connection, statement, preparedStatement);
    }

    @Test
    public void testSaveShouldDoNothingWhenThereIsAnSqlException() throws SQLException {
        // Given
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        underTest.insertPlayer(player);

        // Then
        Mockito.verifyNoMoreInteractions(connection);
    }

    @Test
    public void testSelectShouldDoNothingWhenThereIsAnSqlException() throws SQLException {
        // Given
        Mockito.when(connection.createStatement()).thenThrow(new SQLException());

        // When
        underTest.selectPlayer("Béla");

        // Then
        Mockito.verifyNoMoreInteractions(connection);
    }

    /*@Test
    public void testCloseShouldDelegateCloseCallToConnection() throws Exception {
        // When
        underTest.close();

        // Then
        Mockito.verify(connection).close();
        Mockito.verifyNoMoreInteractions();
    }*/

    @Test
    public void testLoadShouldReturnAPlayerWhenThereIsNoException() throws SQLException {
        // Given
        Player expected = Mockito.mock(Player.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(PlayerDAO.SELECT_STATEMENT)).thenReturn(resultSet);
        String name = "";
        Mockito.when(resultSet.getString("name")).thenReturn(name);

        // When
        Player player = underTest.selectPlayer(name);

        // Then
        Assertions.assertNull(player);
    }

    @Test
    public void testLoadShouldReturnAllPlayerWhenThereIsNoException() throws SQLException {
        // Given
        Player expected = Mockito.mock(Player.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(PlayerDAO.SELECT_ALL_STATEMENT)).thenReturn(resultSet);

        // When
        List<Player> player = underTest.selectAllPlayer();

        // Then
        Assertions.assertNotNull(player);
    }


    @Test
    public void testUpdateShouldUpdatePlayerWhenThereIsNoException() throws SQLException {
        // Given
        Player expected = Mockito.mock(Player.class);
        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        Mockito.when(statement.executeQuery(PlayerDAO.UPDATE_STATEMENT)).thenReturn(resultSet);

        // When
        underTest.updatePlayerDetails(new Player("Norbert", 1, 2));

        // Then
        Assertions.assertNotNull(player);
    }

}