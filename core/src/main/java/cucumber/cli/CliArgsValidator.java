package cucumber.cli;

import java.util.Arrays;
import java.util.List;

public class CliArgsValidator {

    public static final String USAGE = "TODO - Write the help";
    public static final String MISSING_GLUE = "Missing option: --glue";
    public static final int CLEAN_EXIT = 0;
    public static final int ERROR_EXIT = 1;

    private final String[] args;
    private final MessageOutputter messageOutputter;
    private final ProcessExit processExit;
    private final String version;

    public CliArgsValidator(String[] args, MessageOutputter messageOutputter, ProcessExit processExit, String version) {
        this.args = args;
        this.messageOutputter = messageOutputter;
        this.processExit = processExit;
        this.version = version;
    }

    public void processAnyOneOffArgs() {

        List<String> listOfArgs = Arrays.asList(args);

        if (listOfArgs.contains("--help") || listOfArgs.contains("-h")) {
            messageOutputter.print(USAGE);
            processExit.exitWith(CLEAN_EXIT);
        } else if (listOfArgs.contains("--version") || listOfArgs.contains("-v")) {
            messageOutputter.print(version);
            processExit.exitWith(CLEAN_EXIT);
        } else if (listOfArgs.contains("--glue") || listOfArgs.contains("-g")) {
            messageOutputter.print(MISSING_GLUE);
            processExit.exitWith(ERROR_EXIT);
        }
    }
}
