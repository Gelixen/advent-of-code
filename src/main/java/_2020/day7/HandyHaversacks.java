package _2020.day7;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.*;

import static java.util.stream.Collectors.toMap;

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
                .collect(toMap(BagContainer::getContainer, BagContainer::getContained));

        int containedOnlyBagsCount = findAllBagsCount(SEARCHABLE_BAG) - 1;

        log.info(String.valueOf(containedOnlyBagsCount));
    }

    private int findAllBagsCount(String bag) {
        int totalBagsCount = 1;

        for (Bag bagContainer : bagsMap.getOrDefault(bag, Collections.emptyList())) {
            totalBagsCount += (bagContainer.getAmount() * findAllBagsCount(bagContainer.getColor()));
        }

        return totalBagsCount;
    }

}
