package hu.nye.progtech.battleship;

import hu.nye.progtech.battleship.persistence.impl.XmlRepositoryImplementation;
import hu.nye.progtech.battleship.service.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Java Main class for my application.
 */
public class Main {

    /**
     * Java Main class, Entry point.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        ApplicationContext applicationConfiguration = new AnnotationConfigApplicationContext("hu.nye.progtech.battleship");
        Game game = applicationConfiguration.getBean(Game.class);
        game.start();
    }
}