package _2022.day16;

import java.util.List;

public record Valve(String name, int flowRate, List<String> connectedValves) {

}