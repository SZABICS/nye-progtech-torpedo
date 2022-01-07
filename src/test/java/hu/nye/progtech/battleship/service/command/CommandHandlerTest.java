package hu.nye.progtech.battleship.service.command;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Command handler test how it handles.
 */
@ExtendWith(MockitoExtension.class)
public class CommandHandlerTest {

    private static final String INPUT = "input";

    @Mock
    private Command command1;
    @Mock
    private Command command2;

    private CommandHandler underTest;

    @BeforeEach
    public void setUp() {
        underTest = new CommandHandler(List.of(command1, command2));
    }

    @Test
    public void testcommandhandlershouldrunonlytofirstcommand() {
        // given
        given(command1.canProcess(INPUT)).willReturn(true);

        // when
        underTest.handleCommand(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verify(command1).process(INPUT);
        verifyNoInteractions(command2);
    }

    @Test
    public void testCommandHandlerShouldNoRunAnyCommandIfItNotAcceptableOfAny() {
        // given
        given(command1.canProcess(INPUT)).willReturn(false);
        given(command2.canProcess(INPUT)).willReturn(false);

        // when
        underTest.handleCommand(INPUT);

        // then
        verify(command1).canProcess(INPUT);
        verifyNoMoreInteractions(command1);
        verify(command2).canProcess(INPUT);
        verifyNoMoreInteractions(command2);
    }

}