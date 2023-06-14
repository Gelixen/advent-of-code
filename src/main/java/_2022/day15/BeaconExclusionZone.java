package _2022.day15;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;
import java.util.stream.IntStream;

@Log
public class BeaconExclusionZone implements SolvableTask {

    public static final int CAPTURE_LINE_Y = 2_000_000;

    public static void main(String[] args) {
        new BeaconExclusionZone().solve();
    }

    @Override
    public void solve() {
        String[] lines = getInputLines();
        long result = Arrays.stream(lines)
                .map(SensorAndBeaconExtractor::new)
                .map(SensorAndBeaconExtractor::extract)
                .filter(BeaconExclusionZone::isCapturePointWithinSensorRange)
                .flatMapToInt(sensorAndBeacon -> {
                    Coordinate sensor = sensorAndBeacon.sensor();
                    int verticalDistanceToCaptureLine = Math.abs(sensor.y() - CAPTURE_LINE_Y);
                    int remainderForXAxisDistance = sensorAndBeacon.getManhattanDistance() - verticalDistanceToCaptureLine;

                    int captureLineXStart = sensor.x() - remainderForXAxisDistance;
                    int captureLineXEnd = sensor.x() + remainderForXAxisDistance;

                    return IntStream.range(captureLineXStart, captureLineXEnd);
                })
                .distinct()
                .count();

        log.info(String.valueOf(result));
    }

    private static boolean isCapturePointWithinSensorRange(SensorAndBeacon sensorAndBeacon) {
        int sensorY = sensorAndBeacon.sensor().y();
        int radius = sensorAndBeacon.getManhattanDistance();

        return sensorY + radius > CAPTURE_LINE_Y
                && sensorY - radius < CAPTURE_LINE_Y;
    }

}