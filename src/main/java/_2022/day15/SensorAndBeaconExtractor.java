package _2022.day15;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SensorAndBeaconExtractor {
    private static final String REGEX_PATTERN = "Sensor at x=(?<sensorX>-?\\d+), y=(?<sensorY>-?\\d+): closest beacon is at x=(?<beaconX>-?\\d+), y=(?<beaconY>-?\\d+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);
    private final Matcher matcher;

    public SensorAndBeaconExtractor(String line) {
        this.matcher = PATTERN.matcher(line);
    }

    public SensorAndBeacon extract() {
        if (matcher.matches()) {
            int sensorX = parseAsInt("sensorX");
            int sensorY = parseAsInt("sensorY");
            int beaconX = parseAsInt("beaconX");
            int beaconY = parseAsInt("beaconY");

            Coordinate sensor = new Coordinate(sensorX, sensorY);
            Coordinate beacon = new Coordinate(beaconX, beaconY);

            return new SensorAndBeacon(sensor, beacon);
        }

        return null;
    }

    private int parseAsInt(String groupName) {
        return Integer.parseInt(matcher.group(groupName));
    }

}
