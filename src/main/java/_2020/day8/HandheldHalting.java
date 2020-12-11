package _2020.day8;

import lombok.Getter;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class HandheldHalting implements SolvableTask {

    @Getter
    private int accumulatorValue;
    @Getter
    private int nextCommandIndex;

    public static void main(String[] args) {
        new HandheldHalting().solve();
    }

    @Override
    public void solve() {
        String[] commandsMatrix = getInputLines();

        int result = executeCommands(commandsMatrix);

        log.info(String.valueOf(result));
    }

    public void executeCommand(String commandLine) {
        String[] commandWithArgument = commandLine.split(" ");

        String command = commandWithArgument[0];
        int argument = Integer.parseInt(commandWithArgument[1]);

        switch (command) {
            case "acc" -> {
                accumulatorValue += argument;
                nextCommandIndex++;
            }
            case "jmp" -> nextCommandIndex += argument;
            case "nop" -> nextCommandIndex++;
            default -> throw new InvalidCommandException(command);
        }
    }

    public int executeCommands(String[] commands) {
        boolean[] visitedCommands = new boolean[commands.length];

        while (!visitedCommands[nextCommandIndex]) {
            visitedCommands[nextCommandIndex] = true;
            String nextCommand = commands[nextCommandIndex];
            executeCommand(nextCommand);
        }

        return accumulatorValue;
    }
}
