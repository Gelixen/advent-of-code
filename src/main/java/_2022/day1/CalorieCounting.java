package _2022.day1;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Log
public class CalorieCounting implements SolvableTask {
    public static void main(String[] args) {
        new CalorieCounting().solve();
    }

    @Override
    public void solve() {
        List<Integer> elfCarriedCalories = groupCaloriesByElf(getInputLines());

        int top3ElvesWithMostCaloriesSum = elfCarriedCalories.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(i -> i)
                .sum();

        System.out.println(top3ElvesWithMostCaloriesSum);
    }

    private List<Integer> groupCaloriesByElf(String[] caloryLines) {
        List<Integer> elvesCarriedCalories = new ArrayList<>();

        int totalCaloriesByElf = 0;

        for (String line: caloryLines) {
            if (line.isEmpty()) {
                elvesCarriedCalories.add(totalCaloriesByElf);
                totalCaloriesByElf = 0;
            } else {
                totalCaloriesByElf += Integer.parseInt(line);
            }
        }
        return elvesCarriedCalories;
    }
}
