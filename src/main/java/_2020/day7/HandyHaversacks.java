package _2020.day7;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

@Log
public class HandyHaversacks implements SolvableTask {

    private static final String SEARCHABLE_BAG = "shiny gold";

    private Map<String, List<String>> bagsMap;

    public static void main(String[] args) {
        new HandyHaversacks().solve();
    }

    @Override
    public String getPackageName() {
        return MethodHandles.lookup().lookupClass().getPackageName();
    }

    @Override
    public void solve() {
        bagsMap = Arrays.stream(getInputLines())
                .map(BagContainerParser::parseLine)
                .flatMap(this::getReversedContainers)
                .collect(
                        toMap(
                                BagContainer::getContainer,
                                BagContainer::getContained,
                                (bag1, bag2) -> Stream.concat(bag1.stream(), bag2.stream()).collect(Collectors.toList())
                        )
                );

        Set<String> containedBagsCount = findPossibleContainers(SEARCHABLE_BAG);

        log.info(String.valueOf(containedBagsCount.size()));
    }

    private Stream<BagContainer> getReversedContainers(BagContainer bc) {
        return bc.getContained().stream()
                .map(bag -> new BagContainer(bag, Collections.singletonList(bc.getContainer())));
    }

    private Set<String> findPossibleContainers(String bag) {
        List<String> bagContainers = bagsMap.getOrDefault(bag, Collections.emptyList());

        Set<String> allPossibleContainers = new HashSet<>(bagContainers);

        bagContainers.forEach(b -> allPossibleContainers.addAll(findPossibleContainers(b)));

        return allPossibleContainers;
    }

}
