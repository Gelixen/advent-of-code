package _2020.day8;

import java.util.Arrays;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class HandheldHalting implements SolvableTask {

    private int accumulatorValue;
    private int nextCommandIndex;
    private boolean endReached;

    private String[] commandsList = getInputLines();
    private boolean[] visitedCommands = new boolean[commandsList.length];

    public static void main(String[] args) {
        new HandheldHalting().solve();
    }

    @Override
    public void solve() {
        executeCommands(false);
    }

    public void executeCommand(String commandLine, boolean commandSwitchUsed) {
        String[] commandWithArgument = commandLine.split(" ");

        String command = commandWithArgument[0];
        String rawArgument = commandWithArgument[1];
        int argument = Integer.parseInt(rawArgument);

        switch (command) {
            case "acc" -> executeAccumulateCommand(argument);
            case "jmp" -> executeCommand(commandSwitchUsed, rawArgument, Integer.parseInt(rawArgument), "nop");
            case "nop" -> executeCommand(commandSwitchUsed, rawArgument, 1, "jmp");
            default -> throw new InvalidCommandException(command);
        }
    }

    private void executeAccumulateCommand(int argument) {
        visitedCommands[nextCommandIndex] = true;
        accumulatorValue += argument;
        nextCommandIndex += 1;
    }

    private void executeCommand(boolean commandSwitchUsed,
                                String rawArgument,
                                int nextCommandIndexIncrement,
                                String newCommand) {

        if (!commandSwitchUsed && tryCommandSwitch(newCommand, rawArgument)) {
            return;
        }

        visitedCommands[nextCommandIndex] = true;
        nextCommandIndex += nextCommandIndexIncrement;
    }

    private boolean tryCommandSwitch(String newCommand, String rawArgument) {
        String switchedCommand = String.join(" ", newCommand, rawArgument);

        int accumulatorValueBackup = accumulatorValue;
        int nextCommandIndexBackup = nextCommandIndex;
        boolean[] visitedCommandsBackup = visitedCommands.clone();

        String[] commandsListBackup = commandsList.clone();
        commandsList[nextCommandIndex] = switchedCommand;

        executeCommands(true);

        if (endReached) {
            log.info(String.format(
                    "Correct program with switched '%s' command: %s ",
                    switchedCommand,
                    Arrays.toString(commandsList)
            ));
            log.info("Accumulated value: " + accumulatorValue);
            return true;
        }

        nextCommandIndex = nextCommandIndexBackup;
        accumulatorValue = accumulatorValueBackup;
        commandsList = commandsListBackup;
        visitedCommands = visitedCommandsBackup;

        return false;
    }

    public void executeCommands(boolean commandSwitchUsed) {
        while (!endReached && !visitedCommands[nextCommandIndex]) {
            String nextCommand = commandsList[nextCommandIndex];
            executeCommand(nextCommand, commandSwitchUsed);
            endReached = nextCommandIndex >= commandsList.length;
        }
    }

    public int getAccumulatorValue() {
        return accumulatorValue;
    }

    public int getNextCommandIndex() {
        return nextCommandIndex;
    }

    public boolean isEndReached() {
        return endReached;
    }
}