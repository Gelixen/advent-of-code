package _2020.day7;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class BagContainerParser {

    private static final String NO_OTHER_BAGS = "no other bags.";
    private static final String BAGS_CONTAIN_SPLITTER = " bags contain ";
    private static final String CONTAINED_BAGS_SPLITTER = ", ";

    private BagContainerParser() {
    }

    static BagContainer parseLine(String line) {
        String[] splitContainerAndContainedBags = line.split(BAGS_CONTAIN_SPLITTER, 2);

        String containerBag = splitContainerAndContainedBags[0];
        String containedBags = splitContainerAndContainedBags[1];

        if (containedBags.equals(NO_OTHER_BAGS)) {
            return new BagContainer(containerBag, Collections.emptyList());
        }

        String[] splitContainedBags = containedBags.split(CONTAINED_BAGS_SPLITTER);

        List<Bag> trimmedContainedBags = Arrays.stream(splitContainedBags)
                .map(BagContainerParser::mapToBag)
                .collect(Collectors.toList());

        return new BagContainer(containerBag, trimmedContainedBags);
    }

    private static Bag mapToBag(String bag) {
        String[] amountWithColor = bag.split(" ", 2);
        int bagColorEndIndex = bag.indexOf(" bag") - 1;

        String color = amountWithColor[1].substring(0, bagColorEndIndex - 1);
        int amount = Integer.parseInt(amountWithColor[0]);

        return new Bag(color, amount);
    }
}