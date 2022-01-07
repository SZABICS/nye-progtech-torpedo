package hu.nye.progtech.battleship.service.command.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import hu.nye.progtech.battleship.ui.PrintWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * DefaultComman class Unit tests.
 */
@ExtendWith(MockitoExtension.class)
public class DefaultCommandTest {

    private static final String INPUT = "";

    private static final String UNKNOWN_COMMAND_MESSAGE = "Ismeretlen";

    @Mock
    private PrintWrapper printWrapper;

    private DefaultCommand underTest;

    @BeforeEach
    public void setUp() {
        underTest = new DefaultCommand(printWrapper);
    }

    @Test
    public void testProcessReturnTrue() {
        // when
        boolean result = underTest.canProcess(INPUT);

        // then
        assertTrue(result);
    }

    @Test
    public void testMethodShouldPrintUnknowCommand() {
        // when
        underTest.process(INPUT);

        // then
        verify(printWrapper).printLine(UNKNOWN_COMMAND_MESSAGE);
    }

}