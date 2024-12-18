package _2021.day2;

import java.util.Arrays;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class Dive implements SolvableTask {

    private PositionParameters position;

    public Dive(PositionParameters position) {
        this.position = position;
    }

    public static void main(String[] args) {
        new Dive(new PositionParameters(0, 0, 0)).solve();
    }

    @Override
    public void solve() {
        Arrays.stream(getInputLines())
                .forEach(this::navigate);

        log.info(String.valueOf(position.horizontalPosition() * position.depth()));
    }

    public void navigate(String commandLine) {
        String[] commandWithStep = commandLine.split(" ");

        Command command = Command.valueOf(commandWithStep[0].toUpperCase());
        String rawStep = commandWithStep[1];
        int step = Integer.parseInt(rawStep);

        position = command.execute(position, step);
    }
}