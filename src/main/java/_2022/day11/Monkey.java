package _2022.day11;

import java.util.List;
import java.util.function.UnaryOperator;

public record Monkey(int id,
                     List<Integer> worryLevels,
                     UnaryOperator<Integer> operation,
                     Test test) {

}