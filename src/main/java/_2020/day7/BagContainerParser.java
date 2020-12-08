package _2020.day7;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BagContainerParser {

    private static final String NO_OTHER_BAGS = "no other bags.";
    private static final String BAGS_CONTAIN_SPLITTER = " bags contain ";
    private static final String CONTAINED_BAGS_SPLITTER = ", ";

    static BagContainer parseLine(String line) {
        String[] splitContainerAndContainedBags = line.split(BAGS_CONTAIN_SPLITTER, 2);

        String containerBag = splitContainerAndContainedBags[0];
        String containedBags = splitContainerAndContainedBags[1];

        if (containedBags.equals(NO_OTHER_BAGS)) {
            return new BagContainer(containerBag, Collections.emptyList());
        }

        String[] splitContainedBags = containedBags.split(CONTAINED_BAGS_SPLITTER);

        List<String> trimmedContainedBags = Arrays.stream(splitContainedBags)
                .map(BagContainerParser::trimTillColorOnly)
                .collect(Collectors.toList());

        return new BagContainer(containerBag, trimmedContainedBags);
    }

    private static String trimTillColorOnly(String bag) {
        int bagColorStartIndex = bag.indexOf(" ") + 1;
        int bagColorEndIndex = bag.indexOf(" bag");
        return bag.substring(bagColorStartIndex, bagColorEndIndex);
    }
}