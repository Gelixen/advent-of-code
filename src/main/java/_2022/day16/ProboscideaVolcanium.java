package _2022.day16;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class ProboscideaVolcanium implements SolvableTask {

    private static final String STARTING_VALVE = "AA";
    private static final int MINUTES_LEFT = 30;

    public static void main(String[] args) {
        new ProboscideaVolcanium().solve();
    }

    @Override
    public void solve() {
        String[] lines = getInputLines();

        Map<String, Valve> valveMap = Arrays.stream(lines)
                .map(DataExtractor::new)
                .map(DataExtractor::extract)
                .collect(Collectors.toMap(Valve::name, zone -> zone));

        Map<String, Map<String, Integer>> allDistancesMap = getAllValvesDistancesMap(valveMap);

        int mostPressure = calculateMostPressure(valveMap, allDistancesMap);

        log.info(String.valueOf(mostPressure));
    }

    private int calculateMostPressure(Map<String, Valve> valveMap,
            Map<String, Map<String, Integer>> allDistancesMap) {

        Map<String, Valve> valvesToVisit = valveMap.keySet().stream()
                .map(valveMap::get)
                .filter(valve -> valve.flowRate() > 0 || valve.name().endsWith(STARTING_VALVE))
                .collect(Collectors.toMap(Valve::name, Function.identity()));

        return calculateMostPressure(
                valveMap,
                allDistancesMap,
                valvesToVisit,
                STARTING_VALVE,
                MINUTES_LEFT
        );
    }

    private int calculateMostPressure(Map<String, Valve> valveMap,
            Map<String, Map<String, Integer>> allDistancesMap,
            Map<String, Valve> valvesToVisit,
            String currentValveName,
            int remainingMinutes) {

        HashMap<String, Valve> valvesToVisitCopy = new HashMap<>(valvesToVisit);
        valvesToVisitCopy.remove(currentValveName);

        return valvesToVisitCopy.keySet().stream()
                .mapToInt(valveName -> {
                    Integer distanceFromCurrent = allDistancesMap.get(currentValveName)
                            .get(valveName);

                    if (remainingMinutes <= distanceFromCurrent - 1) {
                        return 0;
                    }

                    int leftOverTimeAfterTravelAndOpen = remainingMinutes - distanceFromCurrent - 1;
                    int pressureBuildUpTillEnd =
                            valveMap.get(valveName).flowRate() * leftOverTimeAfterTravelAndOpen;

                    return pressureBuildUpTillEnd +
                            calculateMostPressure(valveMap,
                                    allDistancesMap,
                                    valvesToVisitCopy,
                                    valveName,
                                    leftOverTimeAfterTravelAndOpen
                            );
                })
                .max()
                .orElse(0);
    }

    private Map<String, Map<String, Integer>> getAllValvesDistancesMap(Map<String, Valve> valveMap) {
        return valveMap.keySet().stream()
                .map(key -> Map.entry(key, getValveDistanceMap(valveMap, key)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));

    }

    private Map<String, Integer> getValveDistanceMap(Map<String, Valve> valveMap,
            String startingValve) {
        Queue<String> toVisit = new ArrayDeque<>();
        Map<String, Integer> distanceMap = valveMap.keySet().stream()
                .collect(Collectors.toMap(Function.identity(), __ -> Integer.MAX_VALUE));

        toVisit.add(startingValve);
        distanceMap.replace(startingValve, 0);

        while (!toVisit.isEmpty()) {
            String currentValveName = toVisit.poll();
            Valve currentValve = valveMap.get(currentValveName);

            int finalDistance = distanceMap.get(currentValveName) + 1;
            currentValve.connectedValves().stream()
                    .filter(name -> isNotVisited(distanceMap, name))
                    .forEach(name -> {
                        distanceMap.replace(name, finalDistance);
                        toVisit.add(name);
                    });
        }

        return distanceMap;
    }

    private static boolean isNotVisited(Map<String, Integer> distanceMap, String valveName) {
        return distanceMap.get(valveName) == Integer.MAX_VALUE;
    }

}