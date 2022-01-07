package hu.nye.progtech.battleship.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.nye.progtech.battleship.models.player.PlayerInterface;
import hu.nye.progtech.battleship.models.player.impl.Player;
import hu.nye.progtech.battleship.persistence.PlayerDAOInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PlayerDAO class for Database connection, and Player data manipulation.
 */
public class PlayerDAO implements PlayerDAOInterface, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerDAO.class);

    final Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progtech", "root", "");

    static final String CREATE_STATEMENT = "INSERT INTO `players` (name, played_games_count, won_games_count) VALUES (?, ?, ?);";
    static final String SELECT_STATEMENT = "SELECT * FROM `players` WHERE name=?;";
    static final String SELECT_ALL_STATEMENT = "SELECT * FROM `players` ORDER BY won_games_count DESC;";
    static final String UPDATE_STATEMENT = "UPDATE `players` SET played_games_count=?, won_games_count=? WHERE name=?;";

    public PlayerDAO() throws SQLException {
    }

    @Override
    public void insertPlayer(Player player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_STATEMENT)) {

            preparedStatement.setString(1, String.valueOf(player.getName()));
            preparedStatement.setString(2, String.valueOf(player.getNumberOfPlayedGames()));
            preparedStatement.setString(3, String.valueOf(player.getNumberOfGamesWon()));
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Player selectPlayer(String name) {
        Player player = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                player = new Player();
                player.setName(resultSet.getString("name"));
                player.setNumberOfPlayedGames(resultSet.getInt("played_games_count"));
                player.setNumberOfGamesWon(resultSet.getInt("won_games_count"));
            }
            return player;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return player;
    }

    @Override
    public List<Player> selectAllPlayer() {
        List<Player> playersList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATEMENT)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                playersList.add(
                        new Player(resultSet.getString("name"),
                                resultSet.getInt("won_games_count"),
                                resultSet.getInt("played_games_count")));
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return playersList;
    }

    @Override
    public void updatePlayerDetails(Player player) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT)) {
            preparedStatement.setString(1, String.valueOf(player.getNumberOfPlayedGames()));
            preparedStatement.setString(2, String.valueOf(player.getNumberOfGamesWon()));
            preparedStatement.setString(3, player.getName());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
