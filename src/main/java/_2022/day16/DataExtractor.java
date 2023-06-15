package _2022.day16;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataExtractor {

    private static final String REGEX_PATTERN = "Valve (?<name>[A-Z]{2}) has flow rate=(?<flowRate>\\d{1,2}); tunnel[s]? lead[s]? to valve[s]? (?<otherValves>(?:[A-Z]{2}, )*(?:[A-Z]{2}))";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);
    private final Matcher matcher;

    public DataExtractor(String line) {
        this.matcher = PATTERN.matcher(line);
    }

    public Valve extract() {
        if (matcher.matches()) {
            String name = matcher.group("name");
            int flowRate = Integer.parseInt(matcher.group("flowRate"));
            String otherValves = matcher.group("otherValves");

            List<String> connectedValves = Arrays.stream(otherValves.split(", ")).toList();
            return new Valve(name, flowRate, connectedValves);
        }

        return null;
    }

}