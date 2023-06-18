package _2022.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathTraverser {

    private static final String STARTING_VALVE = "AA";
    private static final int MINUTES_LEFT = 26;

    private final Map<String, Valve> valveMap;
    private final Map<String, Map<String, Integer>> distanceMap;
    private final Map<String, Valve> initialValvesToVisit;

    public PathTraverser(Map<String, Valve> valveMap,
            Map<String, Map<String, Integer>> distanceMap) {

        this.valveMap = valveMap;
        this.distanceMap = distanceMap;
        this.initialValvesToVisit = valveMap.keySet().stream()
                .map(valveMap::get)
                .filter(valve -> valve.flowRate() > 0 || valve.name().endsWith(STARTING_VALVE))
                .collect(Collectors.toMap(Valve::name, Function.identity()));
    }

    public List<PathWithPressure> getPathsWithPressure() {
        return getPathsWithPressure(
                initialValvesToVisit,
                STARTING_VALVE,
                MINUTES_LEFT
        );
    }

    public List<PathWithPressure> getPathsWithPressure(
            Map<String, Valve> valvesToVisit,
            String currentValveName,
            int remainingMinutes) {

        valvesToVisit.remove(currentValveName);

        if (valvesToVisit.isEmpty()) {
            return List.of(PathWithPressure.empty());
        }

        return valvesToVisit.keySet().stream()
                .flatMap(valveName -> {
                    Integer distanceFromCurrent = distanceMap.get(currentValveName)
                            .get(valveName);

                    int travelAndOpenTime = distanceFromCurrent + 1;
                    if (remainingMinutes <= travelAndOpenTime) {
                        return Stream.of(PathWithPressure.empty());
                    }

                    int leftOverTimeAfterTravelAndOpen = remainingMinutes - travelAndOpenTime;
                    int pressureAccumulationTillEnd =
                            valveMap.get(valveName).flowRate() * leftOverTimeAfterTravelAndOpen;

                    List<PathWithPressure> nestedPressure = getPathsWithPressure(
                            new HashMap<>(valvesToVisit),
                            valveName,
                            leftOverTimeAfterTravelAndOpen
                    );

                    return nestedPressure.stream()
                            .map(result -> result.add(valveName, pressureAccumulationTillEnd));
                }).collect(Collectors.toList());
    }

}