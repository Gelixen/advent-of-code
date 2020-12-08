package _2020.day7;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BagContainerParserTest {

    private static Stream<Arguments> parseLine() {
        return Stream.of(
                Arguments.of(
                        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
                        "light red",
                        List.of("bright white", "muted yellow")
                ),
                Arguments.of(
                        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
                        "dark orange",
                        List.of("bright white", "muted yellow")
                ),
                Arguments.of(
                        "bright white bags contain 1 shiny gold bag.",
                        "bright white",
                        List.of("shiny gold")
                ),
                Arguments.of(
                        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
                        "muted yellow",
                        List.of("shiny gold", "faded blue")
                ),
                Arguments.of(
                        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
                        "shiny gold",
                        List.of("dark olive", "vibrant plum")
                ),
                Arguments.of(
                        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
                        "dark olive",
                        List.of("faded blue", "dotted black")
                ),
                Arguments.of(
                        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
                        "vibrant plum",
                        List.of("faded blue", "dotted black")
                ),
                Arguments.of(
                        "faded blue bags contain no other bags.",
                        "faded blue",
                        emptyList()
                ),
                Arguments.of(
                        "dotted black bags contain no other bags.",
                        "dotted black",
                        emptyList()
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void parseLine(String line, String expectedContainer, List<String> expectedContained) {
        BagContainer result = BagContainerParser.parseLine(line);

        assertEquals(expectedContainer, result.getContainer());
        assertEquals(expectedContained, result.getContained());
    }
}