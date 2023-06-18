package _2022.day16;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.java.Log;
import util.SolvableTask;

@Log
public class ProboscideaVolcanium implements SolvableTask {

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
            Map<String, Map<String, Integer>> distancesMap) {

        List<PathWithPressure> pathsList =
                new PathTraverser(valveMap, distancesMap).getPathsWithPressure();

        List<PathWithPressure> distinctPathsList = pathsList.stream().distinct().toList();

        return distinctPathsList.parallelStream()
                .mapToInt(pathWithPressure -> {
                    LinkedHashSet<String> path = pathWithPressure.path();
                    int pressure = pathWithPressure.pressure();

                    return distinctPathsList.parallelStream()
                            .filter(nestedPathWithPressure ->
                                    Collections.disjoint(path, nestedPathWithPressure.path())
                            )
                            .mapToInt(nestedPath -> nestedPath.pressure() + pressure)
                            .max()
                            .orElseThrow();

                })
                .max()
                .orElseThrow();
    }

    private Map<String, Map<String, Integer>> getAllValvesDistancesMap(
            Map<String, Valve> valveMap) {
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