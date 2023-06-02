package _2022.day1;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Log
public class CalorieCounting implements SolvableTask {
    public static void main(String[] args) {
        new CalorieCounting().solve();
    }

    @Override
    public void solve() {
        List<Integer> elfCarriedCalories = groupCaloriesByElf(getInputLines());
        
        System.out.println(Collections.max(elfCarriedCalories));
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
