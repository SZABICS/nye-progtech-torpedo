package hu.nye.progtech.battleship.configurations;

import hu.nye.progtech.battleship.service.Game;
import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Application Configuration for Torpedo game. */
@Configuration
public class ApplicationConfiguration {

    @Bean
    public Game game(PrintWrapper printWrapper) {
        return new Game(printWrapper);
    }

    @Bean
    public PrintWrapper printWrapper() {
        return new PrintWrapper();
    }
}