package _2020.day7;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class HandyHaversacks implements SolvableTask {

    private static final String SEARCHABLE_BAG = "shiny gold";

    private Map<String, List<Bag>> bagsMap;

    public static void main(String[] args) {
        new HandyHaversacks().solve();
    }

    @Override
    public void solve() {
        bagsMap = Arrays.stream(getInputLines())
                .map(BagContainerParser::parseLine)
                .collect(toMap(BagContainer::container, BagContainer::contained));

        int containedOnlyBagsCount = findAllBagsCount(SEARCHABLE_BAG) - 1;

        log.info(String.valueOf(containedOnlyBagsCount));
    }

    private int findAllBagsCount(String bag) {
        int totalBagsCount = 1;

        for (Bag bagContainer : bagsMap.getOrDefault(bag, Collections.emptyList())) {
            totalBagsCount += (bagContainer.amount() * findAllBagsCount(bagContainer.color()));
        }

        return totalBagsCount;
    }

}