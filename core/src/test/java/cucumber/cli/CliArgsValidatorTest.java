package cucumber.cli;

import org.junit.Test;

import static cucumber.cli.CliArgsValidator.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CliArgsValidatorTest {

    public static final String CUCUMBER_VERSION = "some version";

    @Test
    public void shouldReturnFalseAndDisplayRelevantMessage() {

        assertCorrectResponseToInput("--help", USAGE, CLEAN_EXIT);
        assertCorrectResponseToInput("-h", USAGE, CLEAN_EXIT);
        assertCorrectResponseToInput("--glue", MISSING_GLUE, ERROR_EXIT);
        assertCorrectResponseToInput("-g", MISSING_GLUE, ERROR_EXIT);
        assertCorrectResponseToInput("--version", CUCUMBER_VERSION, CLEAN_EXIT);
    }

    private void assertCorrectResponseToInput(String commandLineOption, String messageToDisplay, int status) {

        MessageOutputter messageOutputter = mock(MessageOutputter.class);
        ProcessExit processExit = mock(ProcessExit.class);

        CliArgsValidator validator = new CliArgsValidator(new String[]{commandLineOption}, messageOutputter, processExit, CUCUMBER_VERSION);

        validator.processAnyOneOffArgs();

        verify(messageOutputter).print(messageToDisplay);
        verify(processExit).exitWith(status);
    }
}
