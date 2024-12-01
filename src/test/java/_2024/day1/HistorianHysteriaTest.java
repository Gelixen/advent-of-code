package _2024.day1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistorianHysteriaTest {

    @Test
    void part1() {
        assertEquals(3508942, new HistorianHysteria().solve1Part());
    }

    @Test
    void part2() {
        assertEquals(26593248, new HistorianHysteria().solve2Part());
    }
}