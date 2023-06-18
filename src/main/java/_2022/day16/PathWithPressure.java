package _2022.day16;

import java.util.LinkedHashSet;

public record PathWithPressure(LinkedHashSet<String> path, int pressure) {

    public static PathWithPressure empty() {
        return new PathWithPressure(new LinkedHashSet<>(), 0);
    }
    
    public PathWithPressure add(String valveName, int pressure) {
        LinkedHashSet<String> newPath = new LinkedHashSet<>(this.path);
        newPath.add(valveName);
        int newPressure = this.pressure + pressure;

        return new PathWithPressure(newPath, newPressure);
    }
}