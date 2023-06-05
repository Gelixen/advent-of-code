package _2022.day5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RearrangementProcedureExtractor {

    private static final String REGEX_PATTERN = "move (?<amount>\\d+) from (?<from>\\d+) to (?<to>\\d+)";
    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN);
    private final Matcher matcher;

    public RearrangementProcedureExtractor(String line) {
        this.matcher = PATTERN.matcher(line);
    }

    public RearrangementProcedure extract() {
        if (matcher.matches()) {
            int amount = parseAsInt("amount");
            int from = parseAsInt("from");
            int to = parseAsInt("to");

            return new RearrangementProcedure(amount, from, to);
        }

        return null;
    }

    private int parseAsInt(String groupName) {
        return Integer.parseInt(matcher.group(groupName));
    }

}
