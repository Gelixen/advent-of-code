package _2021.day5;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.List;

@Log
public class HydrothermalVenture implements SolvableTask {

    private static final int[][] diagram = new int[1000][1000];

    public HydrothermalVenture() {}

    public static void main(String[] args) {
        new HydrothermalVenture().solve();
    }

    private static void fillDiagram(List<Position> positions) {
        for (Position position : positions) {
            diagram[position.x()][position.y()]++;
        }
    }

    @Override
    public void solve() {
        List<String> linesOfVents = Arrays.stream(getInputLines()).toList();

        linesOfVents.stream()
                .map(LineOfVentsBuilder::build)
                .map(LineOfVents::constructLine)
                .forEach(HydrothermalVenture::fillDiagram);

        long count = countOverlapping();

        log.info(String.valueOf(count));
    }

    private long countOverlapping() {
        return Arrays.stream(diagram)
                .flatMapToInt(Arrays::stream)
                .filter(linesCount -> linesCount >= 2)
                .count();
    }

}
