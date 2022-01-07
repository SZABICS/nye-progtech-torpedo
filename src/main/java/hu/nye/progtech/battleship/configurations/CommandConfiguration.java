package hu.nye.progtech.battleship.configurations;

import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for commands by Java Spring.
 */
@Configuration
public class CommandConfiguration {
/*
    @Bean
    SaveCommand saveCommand(PlayerDAOInterface playerDAOInterface, Player player) {
        return new SaveCommand(playerDAOInterface, player);
    }

    @Bean
    LoadCommand loadCommand(PlayerDAO playerDAO) {
        return new LoadCommand(playerDAO);
    }

    @Bean
    ExitCommand exitCommand(Game game) {
        return new ExitCommand(game);
    }

    @Bean
    DefaultCommand defaultCommand(PrintWrapper printWrapper) {
        return new DefaultCommand(printWrapper);
    }

    @Bean
    PlayerDAOInterface playerDAOInterface() {
        return new PlayerDAOInterface() {
            @Override
            public void insertPlayer(Player player) {

            }

            @Override
            public Player selectPlayer(String name) {
                return null;
            }

            @Override
            public List<Player> selectAllPlayer() {
                return null;
            }

            @Override
            public void updatePlayerDetails(Player player) {

            }
        };
    }*/
}
