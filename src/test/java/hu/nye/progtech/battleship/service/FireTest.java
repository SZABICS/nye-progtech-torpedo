package hu.nye.progtech.battleship.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for Game.
 */
@ExtendWith(MockitoExtension.class)
public class FireTest {

    private Fire underTest;

    private PrintWrapper printWrapper;


    @Test
    public void testTheFireIsInicialized() {
        // given
        underTest = new Fire(printWrapper);

        // then
        Assertions.assertNotNull(underTest);
    }

}